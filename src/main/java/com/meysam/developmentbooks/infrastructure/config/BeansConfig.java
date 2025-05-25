package com.meysam.developmentbooks.infrastructure.config;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.application.book.service.AddNewBookService;
import com.meysam.developmentbooks.application.book.service.FindBookService;
import com.meysam.developmentbooks.application.book.usecase.AddNewBookUseCase;
import com.meysam.developmentbooks.application.book.usecase.FindAllBooksUseCase;
import com.meysam.developmentbooks.application.shoppingbasket.ports.in.CalculateBasketPriceApiPort;
import com.meysam.developmentbooks.application.shoppingbasket.service.CalculateBasketPriceService;
import com.meysam.developmentbooks.application.shoppingbasket.usecase.CalculateBasketPriceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public AddNewBookUseCase addNewBookUseCase(WriteBookPort writeBookPort, ReadBookPort readBookPort) {
        return new AddNewBookService(writeBookPort, readBookPort);
    }

    @Bean
    public FindAllBooksUseCase findAllBooksUseCase(ReadBookPort readBookPort) {
        return new FindBookService(readBookPort);
    }

    @Bean
    public CalculateBasketPriceUseCase calculateBasketPriceUseCase(CalculateBasketPriceApiPort calculateBasketPriceApiPort) {
        return new CalculateBasketPriceService(calculateBasketPriceApiPort);
    }
}
