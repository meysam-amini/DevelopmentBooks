package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public class ReadBookAdapter implements ReadBookPort {


    @Override
    public List<Book> findAll() {
        return List.of();
    }

    @Override
    public Boolean existsByIsbn(String isbn) {
        return null;
    }
}
