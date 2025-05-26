package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public class OptimalGroupDiscount extends GroupDiscount {

    private final List<GroupDiscount> strategies;

    public OptimalGroupDiscount(List<GroupDiscount> strategies) {
        this.strategies = strategies;
    }

    @Override
    public boolean canApply(List<Book> group) {
        return !group.isEmpty(); // Always try to apply the best rule
    }

    @Override
    public double apply(List<Book> group) {
        for (GroupDiscount strategy : strategies) {
            if (strategy.canApply(group)) {
                return strategy.apply(group);
            }
        }
        return group.size() * UNIT_PRICE;
    }
}
