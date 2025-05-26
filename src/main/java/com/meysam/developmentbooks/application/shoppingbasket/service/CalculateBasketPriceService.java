package com.meysam.developmentbooks.application.shoppingbasket.service;

import com.meysam.developmentbooks.application.shoppingbasket.usecase.CalculateBasketPriceUseCase;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.ShoppingBasketPriceCalculator;

import java.math.BigDecimal;

import static com.meysam.developmentbooks.common.constants.MessageConstants.ShoppingBasketMsg.BASKET_IS_NULL;

public class CalculateBasketPriceService implements CalculateBasketPriceUseCase {

    private final ShoppingBasketPriceCalculator basketPriceCalculator;

    public CalculateBasketPriceService(ShoppingBasketPriceCalculator basketPriceCalculator) {
        this.basketPriceCalculator = basketPriceCalculator;
    }


    @Override
    public BigDecimal calculateTotalPrice(ShoppingBasket basket) {
        if(basket==null){
            throw new IllegalArgumentException(BASKET_IS_NULL);
        }
        return basketPriceCalculator.calculate(basket);
    }
}
