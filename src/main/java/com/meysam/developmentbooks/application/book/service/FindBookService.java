package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.application.book.usecase.BookExistsByIsbnUseCase;
import com.meysam.developmentbooks.application.book.usecase.FindAllBooksUseCase;
import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public class FindBookService implements FindAllBooksUseCase , BookExistsByIsbnUseCase {

    private final ReadBookPort readBookPort;

    public FindBookService(ReadBookPort readBookPort) {
        this.readBookPort = readBookPort;
    }

    @Override
    public List<Book> findAllBooks(BookSearchCriteria bookSearchCriteria) {
        return readBookPort.findAll(bookSearchCriteria);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return readBookPort.existsByIsbn(isbn);
    }
}
