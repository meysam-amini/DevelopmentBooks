package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.usecase.FindAllBooksUseCase;

public class FindBookService implements FindAllBooksUseCase {

    private final ReadBookPort readBookPort;

    public FindBookService(ReadBookPort readBookPort) {
        this.readBookPort = readBookPort;
    }
}
