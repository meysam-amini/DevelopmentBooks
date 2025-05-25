package com.meysam.developmentbooks.adapters.shoppingbasket.in.web;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.CalculateBasketPriceCommand;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.annotations.Mapper;

import static com.meysam.developmentbooks.common.constants.MessageConstants.ShoppingBasketMsg.CALCULATE_PRICE_COMMAND_IS_NULL;

@Mapper
public class ShoppingBasketWebMapper {

    public ShoppingBasket toDomain(CalculateBasketPriceCommand command) {
        if (command == null)
            throw new IllegalArgumentException(CALCULATE_PRICE_COMMAND_IS_NULL);

        return new ShoppingBasket(null,command.items());
    }
}
