package com.meysam.developmentbooks.adapters.shoppingbasket.in.web;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.CalculateBasketPriceApiPort;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.annotations.Adapter;

import java.math.BigDecimal;

@Adapter
public class CalculateBasketPriceApiAdapter implements CalculateBasketPriceApiPort {

    @Override
    public BigDecimal calculatePrice(ShoppingBasket basket) {
        return null;
    }
}
