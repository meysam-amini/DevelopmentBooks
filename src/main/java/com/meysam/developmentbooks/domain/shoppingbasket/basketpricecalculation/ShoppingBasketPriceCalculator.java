package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.config.DiscountStrategyFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingBasketPriceCalculator {

    private final GroupDiscount discountChain;

    public ShoppingBasketPriceCalculator(DiscountStrategyFactory factory) {
        this.discountChain = factory.createStrategyChain();
    }

    public BigDecimal calculate(ShoppingBasket basket) {
        Map<Book, Integer> bookCounts = basket.getItems().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().value()));

        double total = 0.0;

        while (!bookCounts.isEmpty()) {
            List<Book> group = bookCounts.keySet().stream().limit(5).collect(Collectors.toList());

            double groupPrice = discountChain.calculate(group);
            total += groupPrice;


            for (Book book : group) {
                int count = bookCounts.get(book) - 1;
                if (count == 0) bookCounts.remove(book);
                else bookCounts.put(book, count);
            }
        }

        return BigDecimal.valueOf(total);
    }
}