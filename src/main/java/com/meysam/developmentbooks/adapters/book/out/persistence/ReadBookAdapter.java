package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.infrastructure.annotations.Adapter;

import java.util.List;

@Adapter
public class ReadBookAdapter implements ReadBookPort {

    private final BookRepository bookRepository;
    private final BookJpaMapper mapper;

    public ReadBookAdapter(BookRepository bookRepository, BookJpaMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Book> findAll(BookSearchCriteria searchCriteria) {
        return bookRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}
