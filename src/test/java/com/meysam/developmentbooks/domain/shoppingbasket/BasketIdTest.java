package com.meysam.developmentbooks.domain.shoppingbasket;

import com.meysam.developmentbooks.domain.shoppingbasket.BasketId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasketIdTest {

    @Test
    void createsValidBasketId() {
        BasketId basketId = new BasketId(1L);
        assertEquals(1L, basketId.value());
    }

    @Test
    void throwsWhenValueIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new BasketId(-1L));
        assertThrows(IllegalArgumentException.class, () -> new BasketId(0L));
    }

}