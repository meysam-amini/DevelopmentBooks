package com.meysam.developmentbooks.adapters.shoppingbasket.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meysam.developmentbooks.adapters.book.in.web.mapper.BookWebMapper;
import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.dto.BookQuantityDto;
import com.meysam.developmentbooks.application.shoppingbasket.usecase.CalculateBasketPriceUseCase;
import com.meysam.developmentbooks.domain.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.meysam.developmentbooks.utils.BookSamples.getSampleBook;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShoppingBasketControllerTest {

    private static final String CALCULATE_PRICE_PATH = "/api/v1/shopping-basket/calculate-price";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CalculateBasketPriceUseCase calculateBasketPriceUseCase;
    @Autowired
    private ShoppingBasketWebMapper ShoppingBasketWebMapper;
    @Autowired
    private BookWebMapper bookWebMapper;

    private CalculateBasketPriceApiAdapter calculateBasketPriceApiAdapter;

    List<BookQuantityDto> command = new ArrayList<>();
    @BeforeEach
    void setUp() {

        Book book1 = getSampleBook(0);
        Book book2 = getSampleBook(1);
        Book book3 = getSampleBook(2);
        Book book4 = getSampleBook(3);
        command.add(BookQuantityDto.make(book1,2));
        command.add(BookQuantityDto.make(book1,2));
        command.add(BookQuantityDto.make(book2,2));
        command.add(BookQuantityDto.make(book3,1));
        command.add(BookQuantityDto.make(book4,1));

        calculateBasketPriceApiAdapter = new CalculateBasketPriceApiAdapter(calculateBasketPriceUseCase,ShoppingBasketWebMapper);
    }

    @Test
    void calculatePriceShouldReturn200WhenApiExists() throws Exception {

        mockMvc.perform(post(CALCULATE_PRICE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(status().isOk());
    }


    @Test
    void calculatePriceShouldApplyCorrectDiscounts() throws Exception {

        mockMvc.perform(post(CALCULATE_PRICE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andExpect(jsonPath("$.data").value(320));
    }
}
