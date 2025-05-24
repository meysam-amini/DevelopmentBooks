package developmentbooks.domain.shoppingbasket;

import developmentbooks.domain.book.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketItemTest {

    @Test
    void createsValidBasketItem() {
        BasketItem item = new BasketItem(generateBook(),new Quantity(1));

        assertEquals(1,item.quantity().value());
        assertEquals(generateBook(),item.book());
    }

    @Test
    void throwsWhenBookOrQuantityIsNull() {
        assertThrows(NullPointerException.class,()-> new BasketItem(null,new Quantity(1)));
        assertThrows(NullPointerException.class,()-> new BasketItem(generateBook(),null));
    }

    private Book generateBook() {
        return new Book(
                new BookId(1L), new Title("Test"),
                new Author("Test"), new PublicationYear(1994));
    }

}