package com.meysam.developmentbooks.domain.shoppingbasket;

import com.meysam.developmentbooks.domain.book.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShoppingBasketTest {

    @Test
    void createsBasketWithValidParameters() {
        BasketId basketId = new BasketId(1L);
        HashMap<Book, Quantity> items = new HashMap<>();
        Quantity quantity1 = new Quantity(1);
        Book book = createTestBook();

        items.put(book,quantity1);

        ShoppingBasket basket = new ShoppingBasket(basketId,items);

        assertEquals(basketId, basket.getId());
        assertEquals(quantity1,items.get(book));
    }

    @Test
    void throwsWhenAnyParameterIsNull() {
        HashMap<Book,Quantity> items = new HashMap<>();
        Quantity quantity1 = new Quantity(1);
        Book book = createTestBook();

        items.put(book,quantity1);
        assertThrows(IllegalArgumentException.class, () -> new ShoppingBasket(null, items));
        assertThrows(IllegalArgumentException.class, () -> new ShoppingBasket(new BasketId(1L), null));
    }


    private Book createTestBook() {
        return new Book(
                new BookId(1L),
                new BookIsbn("1234"),
                new Title("Clean Code"),
                new Author("Robert Martin"),
                new PublicationYear(2008)
        );
    }

}