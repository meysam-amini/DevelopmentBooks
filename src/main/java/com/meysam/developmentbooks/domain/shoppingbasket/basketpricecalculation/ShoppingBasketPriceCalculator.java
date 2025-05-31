package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.config.DiscountRulesConfig;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;

public class ShoppingBasketPriceCalculator {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private static final double BOOK_PRICE = 50.0;
    private HashMap<Integer,Double> discountRulesMap;

    public BigDecimal calculate(ShoppingBasket basket, DiscountRulesConfig discountRulesConfig) {
        this.discountRulesMap = createDiscountRulesMap(discountRulesConfig);
        Map<Book, Integer> bookCounts = new HashMap<>();
        basket.getItems().forEach((book, quantity) -> bookCounts.put(book, quantity.value()));

        double total = calculateBestTotalPrice(bookCounts);
        return BigDecimal.valueOf(total);
    }

    private HashMap<Integer, Double> createDiscountRulesMap(DiscountRulesConfig discountRulesConfig) {

        HashMap<Integer,Double> h= new HashMap<>();
        discountRulesConfig.getDiscountRules().forEach(groupDiscountRule -> h.put(groupDiscountRule.getSize(),groupDiscountRule.getDiscount()));
        return h;
    }

    private double calculateBestTotalPrice(Map<Book, Integer> bookCounts) {
        List<Integer> counts = new ArrayList<>(bookCounts.values());
        return calculateOptimal(counts, new HashMap<>());
    }

    private double calculateOptimal(List<Integer> counts, Map<List<Integer>, Double> memo) {
        // Normalize counts for memoization key

        counts = counts.stream().filter(c -> c > 0).sorted(Collections.reverseOrder()).toList();

        if (counts.isEmpty()) return 0.0;
        if (memo.containsKey(counts)) {
            System.out.println("map contains already: "+counts);
            return memo.get(counts);
        }

        double minPrice = Double.MAX_VALUE;

        System.out.println("bookCounts size: "+counts.size());
        System.out.println("bookCounts:"+counts);
        System.out.println(" memo:"+memo);


        for (int size = counts.size(); size >= 1; size--) {
            List<Integer> remaining = new ArrayList<>(counts);
            for (int i = 0; i < size; i++) {
                remaining.set(i, remaining.get(i) - 1);
            }


            double discount = getDiscountByGroupSize(size);
            double groupPrice = size * BOOK_PRICE * (1 - discount);
            System.out.println("go to calculate remaining :"+remaining);
            double totalPrice = groupPrice + calculateOptimal(remaining, memo);

            minPrice = Math.min(minPrice, totalPrice);
        }

        memo.put(counts, minPrice);
        return minPrice;
    }

    private double getDiscountByGroupSize(Integer size) {
        return discountRulesMap.get(size);
    }
}
