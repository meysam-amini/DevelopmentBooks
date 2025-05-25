package com.meysam.developmentbooks.application.shoppingbasket.ports.in;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.CalculateBasketPriceCommand;

import java.math.BigDecimal;

public interface CalculateBasketPriceApiPort {

    BigDecimal calculatePrice(CalculateBasketPriceCommand calculateBasketPriceCommand);
}
