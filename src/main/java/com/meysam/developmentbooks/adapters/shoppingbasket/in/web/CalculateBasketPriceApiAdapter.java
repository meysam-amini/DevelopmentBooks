package com.meysam.developmentbooks.adapters.shoppingbasket.in.web;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.CalculateBasketPriceApiPort;
import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.dto.BookQuantityDto;
import com.meysam.developmentbooks.application.shoppingbasket.usecase.CalculateBasketPriceUseCase;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.annotations.Adapter;

import java.math.BigDecimal;
import java.util.List;

@Adapter
public class CalculateBasketPriceApiAdapter implements CalculateBasketPriceApiPort {

    private final CalculateBasketPriceUseCase calculateBasketPriceUseCase;
    private final ShoppingBasketWebMapper ShoppingBasketWebMapper;

    public CalculateBasketPriceApiAdapter(CalculateBasketPriceUseCase calculateBasketPriceUseCase, ShoppingBasketWebMapper shoppingBasketWebMapper) {
        this.calculateBasketPriceUseCase = calculateBasketPriceUseCase;
        ShoppingBasketWebMapper = shoppingBasketWebMapper;
    }

    @Override
    public BigDecimal calculatePrice(List<BookQuantityDto> calculateBasketPriceCommand) {

        ShoppingBasket basket = ShoppingBasketWebMapper.toDomain(calculateBasketPriceCommand);
        return calculateBasketPriceUseCase.calculateTotalPrice(basket);
    }
}
