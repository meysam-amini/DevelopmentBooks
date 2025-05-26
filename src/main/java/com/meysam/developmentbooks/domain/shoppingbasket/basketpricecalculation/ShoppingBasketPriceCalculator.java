package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.config.DiscountStrategyFactory;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ShoppingBasketPriceCalculator {

    private final GroupDiscount discountChain;

    public ShoppingBasketPriceCalculator(DiscountStrategyFactory factory) {
        this.discountChain = factory.createStrategyChain();
    }

    public BigDecimal calculate(ShoppingBasket basket) {
        Map<Book, Integer> bookCounts = basket.getItems().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().value()));

        double total = findMinimalTotal(bookCounts, new HashMap<>());
        return BigDecimal.valueOf(total);
    }

    private double findMinimalTotal(Map<Book, Integer> remaining, Map<Map<Book, Integer>, Double> memory) {
        if (remaining.isEmpty()) return 0.0;

        Map<Book, Integer> stateKey = new HashMap<>(remaining);
        if (memory.containsKey(stateKey)) {
            return memory.get(stateKey);
        }

        double minTotal = Double.MAX_VALUE;

        List<Book> uniqueBooks = new ArrayList<>(remaining.keySet());
        int n = uniqueBooks.size();

        // checking subsets of distinct books
        for (int size = 1; size <= n; size++) {
            List<List<Book>> combinations = combinations(uniqueBooks, size);
            for (List<Book> group : combinations) {
                Map<Book, Integer> newRemaining = new HashMap<>(remaining);
                boolean valid = true;
                for (Book book : group) {
                    int count = newRemaining.get(book);
                    if (count == 1) newRemaining.remove(book);
                    else newRemaining.put(book, count - 1);
                }
                if (valid) {
                    double cost = discountChain.calculate(group) + findMinimalTotal(newRemaining, memory);
                    minTotal = Math.min(minTotal, cost);
                }
            }
        }

        memory.put(stateKey, minTotal);
        return minTotal;
    }

    private List<List<Book>> combinations(List<Book> books, int size) {
        List<List<Book>> result = new ArrayList<>();
        backtrack(books, size, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(List<Book> books, int size, int start, List<Book> current, List<List<Book>> result) {
        if (current.size() == size) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < books.size(); i++) {
            current.add(books.get(i));
            backtrack(books, size, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }
}