package com.meysam.developmentbooks.domain.shoppingbasket;

import jakarta.annotation.Nullable;

import java.util.Objects;

import static com.meysam.developmentbooks.common.constants.MessageConstants.ShoppingBasketMsg.BASKET_ID_NOT_POSITIVE;

public record BasketId(@Nullable Long value) {

    public BasketId {
        if (Objects.nonNull(value) && value <= 0L) {
            throw new IllegalArgumentException(BASKET_ID_NOT_POSITIVE);
        }
    }
}
