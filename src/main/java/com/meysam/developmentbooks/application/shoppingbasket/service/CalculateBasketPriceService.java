package com.meysam.developmentbooks.application.shoppingbasket.service;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.CalculateBasketPriceApiPort;
import com.meysam.developmentbooks.application.shoppingbasket.usecase.CalculateBasketPriceUseCase;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;

import java.math.BigDecimal;

public class CalculateBasketPriceService implements CalculateBasketPriceUseCase {

    private final CalculateBasketPriceApiPort calculateBasketPriceApiPort;

    public CalculateBasketPriceService(CalculateBasketPriceApiPort calculateBasketPriceApiPort) {
        this.calculateBasketPriceApiPort = calculateBasketPriceApiPort;
    }


    @Override
    public BigDecimal calculateTotalPrice(ShoppingBasket basket) {
        return null;
    }
}
