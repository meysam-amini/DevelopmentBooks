package com.meysam.developmentbooks.application.shoppingbasket.ports.in.command;

import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.domain.shoppingbasket.Quantity;

import java.util.HashMap;

public record CalculateBasketPriceCommand(
        HashMap<Book, Quantity> items
) {

}
