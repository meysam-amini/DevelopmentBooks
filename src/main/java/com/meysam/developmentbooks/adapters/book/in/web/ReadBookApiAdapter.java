package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.application.book.ports.in.ReadBookApiPort;
import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public class ReadBookApiAdapter implements ReadBookApiPort {

    @Override
    public List<Book> findAllBooks(FindBooksQuery FindBooksQuery) {
        return List.of();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return false;
    }
}
