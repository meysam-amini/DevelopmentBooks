package com.meysam.developmentbooks.application.shoppingbasket.ports.in;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.dto.BookQuantityDto;

import java.math.BigDecimal;
import java.util.List;

public interface CalculateBasketPriceApiPort {

    BigDecimal calculatePrice( List<BookQuantityDto> calculateBasketPriceCommand);
}
