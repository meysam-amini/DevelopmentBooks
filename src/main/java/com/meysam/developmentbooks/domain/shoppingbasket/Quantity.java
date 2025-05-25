package com.meysam.developmentbooks.domain.shoppingbasket;

import static com.meysam.developmentbooks.common.constants.MessageConstants.ShoppingBasketMsg.QUANTITY_NOT_POSITIVE;

public record Quantity(int value) {

    public Quantity {
        if (value <= 0L) {
            throw new IllegalArgumentException(QUANTITY_NOT_POSITIVE);
        }
    }
}
