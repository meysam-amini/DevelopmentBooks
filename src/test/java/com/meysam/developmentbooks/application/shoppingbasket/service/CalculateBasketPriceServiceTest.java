package com.meysam.developmentbooks.application.shoppingbasket.service;

import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.domain.shoppingbasket.BasketId;
import com.meysam.developmentbooks.domain.shoppingbasket.Quantity;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.ShoppingBasketPriceCalculator;
import com.meysam.developmentbooks.infrastructure.config.DiscountProperties;
import com.meysam.developmentbooks.infrastructure.config.DiscountStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.meysam.developmentbooks.utils.BookSamples.getSampleBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculateBasketPriceServiceTest {

    private CalculateBasketPriceService service;

    @BeforeEach
    void setUp() {
        DiscountProperties properties = new DiscountProperties();
        properties.setDiscountGroups(List.of(
                rule(5, 0.25),
                rule(4, 0.20),
                rule(3, 0.10),
                rule(2, 0.05),
                rule(1, 0.0)
        ));

        DiscountStrategyFactory factory = new DiscountStrategyFactory(properties);
        ShoppingBasketPriceCalculator calculator = new ShoppingBasketPriceCalculator(factory);
        service = new CalculateBasketPriceService(calculator);
    }

    private DiscountProperties.GroupDiscountRule rule(int size, double rate) {
        DiscountProperties.GroupDiscountRule rule = new DiscountProperties.GroupDiscountRule();
        rule.setSize(size);
        rule.setDiscount(rate);
        return rule;
    }




    @Test
    void shouldReturnZeroForEmptyBasket() {
        ShoppingBasket basket = new ShoppingBasket(new BasketId(1L), new HashMap<>());
        BigDecimal total = service.calculateTotalPrice(basket);
        assertThat(total).isEqualByComparingTo("0.0");
    }

    @Test
    void shouldThrowExceptionForNullBasket() {
        assertThatThrownBy(() -> service.calculateTotalPrice(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldCalculateCorrectPriceForSingleBook() {
        Map<Book, Quantity> items = Map.of(getSampleBook(0), new Quantity(1));
        ShoppingBasket basket = new ShoppingBasket(new BasketId(1L), new HashMap<>(items));
        BigDecimal total = service.calculateTotalPrice(basket);
        assertThat(total).isEqualByComparingTo("50.0");
    }

    @Test
    void shouldCalculateCorrectPriceForMultipleSameBooks() {
        Map<Book, Quantity> items = Map.of(getSampleBook(1), new Quantity(3));
        ShoppingBasket basket = new ShoppingBasket(new BasketId(1L), new HashMap<>(items));
        BigDecimal total = service.calculateTotalPrice(basket);
        assertThat(total).isEqualByComparingTo("150.0");
    }

    @Test
    void shouldApplyDiscountForDifferentBooks() {
        HashMap<Book, Quantity> items = new HashMap<>();
        items.put(getSampleBook(0), new Quantity(1));
        items.put(getSampleBook(1), new Quantity(1));
        items.put(getSampleBook(2), new Quantity(1));

        ShoppingBasket basket = new ShoppingBasket(new BasketId(1L), items);
        BigDecimal total = service.calculateTotalPrice(basket);
        assertThat(total).isEqualByComparingTo("135.0"); // 3 * 50 * 0.90
    }


    @Test
    void shouldCalculateComplexExampleCorrectly() {
        Book b1 = getSampleBook(0);
        Book b2 = getSampleBook(1);
        Book b3 = getSampleBook(2);
        Book b4 = getSampleBook(3);

        HashMap<Book, Quantity> items = new HashMap<>();
        items.put(b1, new Quantity(2));
        items.put(b2, new Quantity(2));
        items.put(b3, new Quantity(2));
        items.put(b4, new Quantity(1));

        ShoppingBasket basket = new ShoppingBasket(new BasketId(1L), items);
        BigDecimal total = service.calculateTotalPrice(basket);

        // Group1: b1, b2, b3, b4 → 160
        // Group2: b1, b2, b3     → 135
        // Total:                → 295
        assertThat(total).isEqualByComparingTo("295.0");
    }


}
