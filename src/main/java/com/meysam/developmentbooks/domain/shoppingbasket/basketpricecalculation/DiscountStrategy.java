package com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public interface DiscountStrategy {
    boolean canApply(List<Book> group);
    double apply(List<Book> group);
}
