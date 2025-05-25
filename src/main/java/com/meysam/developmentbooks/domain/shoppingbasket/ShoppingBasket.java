package com.meysam.developmentbooks.domain.shoppingbasket;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.*;

import static com.meysam.developmentbooks.common.constants.MessageConstants.ShoppingBasketMsg.BASKET_CONTAINS_NULLS;

public class ShoppingBasket {

    private final BasketId id;
    private final HashMap<Book,Quantity> items;

    public ShoppingBasket(BasketId id) {
        this(id, new HashMap<>());
    }

    public ShoppingBasket(BasketId id, HashMap<Book,Quantity> items) {
        if (id == null || (items == null || items.entrySet().stream().anyMatch(Objects::isNull))) {
            throw new IllegalArgumentException(BASKET_CONTAINS_NULLS);
        }

        this.id = id;
        this.items = items;
    }


    public BasketId getId() {
        return id;
    }

    public Map<Book,Quantity> getItems() {
        return Collections.unmodifiableMap(items);
    }

}
