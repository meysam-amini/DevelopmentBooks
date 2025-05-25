package com.meysam.developmentbooks.application.shoppingbasket.service;

import com.meysam.developmentbooks.application.shoppingbasket.usecase.CalculateBasketPriceUseCase;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;

import java.math.BigDecimal;

public class CalculateBasketPriceService implements CalculateBasketPriceUseCase {


    @Override
    public BigDecimal calculateTotalPrice(ShoppingBasket basket) {
        return null;
    }
}
