package com.meysam.developmentbooks.application.shoppingbasket.usecase;

import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;

import java.math.BigDecimal;

public interface CalculateBasketPriceUseCase {

    BigDecimal calculateTotalPrice(ShoppingBasket basket);
}
