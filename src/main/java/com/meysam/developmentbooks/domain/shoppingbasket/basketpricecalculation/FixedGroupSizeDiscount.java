package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public class FixedGroupSizeDiscount extends GroupDiscount {

    private final int groupSize;
    private final double discountRate;

    public FixedGroupSizeDiscount(int groupSize, double discountRate) {
        this.groupSize = groupSize;
        this.discountRate = discountRate;
    }

    @Override
    public boolean canApply(List<Book> group) {
        return group.size() == groupSize;
    }

    @Override
    public double apply(List<Book> group) {
        return groupSize * UNIT_PRICE * (1 - discountRate);
    }
}
