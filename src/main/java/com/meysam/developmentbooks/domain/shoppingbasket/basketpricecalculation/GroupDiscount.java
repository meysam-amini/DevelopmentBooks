package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public abstract class GroupDiscount implements DiscountStrategy {

    protected static final double UNIT_PRICE = 50.0;
    private GroupDiscount next;

    public GroupDiscount setNext(GroupDiscount next) {
        this.next = next;
        return next;
    }

    /*
    *  Each group Knows how to check if it applies(in canApply method, checks it's size with the current rule size).
       Knows how to calculate the price if it does(in apply method).
       If it doesn’t apply, it passes the responsibility to the next rule.
    *
    * */
    public double calculate(List<Book> group) {
        if (canApply(group)) {
            return apply(group);
        } else if (next != null) {
            return next.calculate(group);
        } else {
            return group.size() * UNIT_PRICE;
        }
    }

    public abstract boolean canApply(List<Book> group);

    public abstract double apply(List<Book> group);
}
