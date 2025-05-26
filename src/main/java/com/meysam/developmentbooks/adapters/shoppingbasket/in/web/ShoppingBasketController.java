package com.meysam.developmentbooks.adapters.shoppingbasket.in.web;

import com.meysam.developmentbooks.adapters.common.ApiResponse;
import com.meysam.developmentbooks.adapters.common.ResponseCodeConstants;
import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.dto.BookQuantityDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import static com.meysam.developmentbooks.common.constants.MessageConstants.Web.SUCCESS;

@RestController
@RequestMapping("api/v1/shopping-basket")
public class ShoppingBasketController {

    private final CalculateBasketPriceApiAdapter calculateBasketPriceApiAdapter;

    public ShoppingBasketController(CalculateBasketPriceApiAdapter calculateBasketPriceApiAdapter) {
        this.calculateBasketPriceApiAdapter = calculateBasketPriceApiAdapter;
    }

    @PostMapping("/calculate-price")
    public ResponseEntity<ApiResponse> calculateBasketPrice(@RequestBody List<BookQuantityDto> command) {
        BigDecimal price = calculateBasketPriceApiAdapter.calculatePrice(command);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(ResponseCodeConstants.OK, price, SUCCESS));
    }

}
