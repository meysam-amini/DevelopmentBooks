package developmentbooks.domain.shoppingbasket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityTest {

    @Test
    void createsValidQuantity() {
        Quantity quantity = new Quantity(10);
        assertEquals(10, quantity.value());
    }

    @Test
    void throwsWhenValueIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity(-1));
        assertThrows(IllegalArgumentException.class, () -> new Quantity(0));
    }

}