package com.meysam.developmentbooks.application.shoppingbasket.ports.in;

import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;

import java.math.BigDecimal;

public interface CalculateBasketPriceApiPort {

    BigDecimal calculatePrice(ShoppingBasket basket);
}
