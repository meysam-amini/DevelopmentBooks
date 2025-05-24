package developmentbooks.domain.shoppingbasket;

import developmentbooks.domain.book.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingBasketTest {

    @Test
    void createsBasketWithValidParameters() {
        BasketId basketId = new BasketId(1L);
        BasketItem basketItem = new BasketItem(createTestBook(),new Quantity(1));

        ShoppingBasket basket = new ShoppingBasket(basketId, Collections.singleton(basketItem));

        assertEquals(basketId,basket.getId());
        assertTrue(basket.getItems().stream().anyMatch(basketItem::equals));
    }

    @Test
    void throwsWhenAnyParameterIsNull() {
        fail("no impl");
    }

    @Test
    void shouldAddBookToBasket() {
        fail("no impl");
    }

    @Test
    void shouldIncreaseQuantityWhenAddingSameBook() {
        fail("no impl");
    }

    @Test
    void shouldKeepSeparateEntriesForDifferentBooks() {
       fail("no impl");
    }

    private Book createTestBook() {
        return new Book(
                new BookId(1L),
                new Title("Clean Code"),
                new Author("Robert Martin"),
                new PublicationYear(2008)
        );
    }
}