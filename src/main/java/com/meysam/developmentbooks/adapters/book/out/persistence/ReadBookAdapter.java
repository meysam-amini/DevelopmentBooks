package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.domain.book.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadBookAdapter implements ReadBookPort {

    private final BookRepository bookRepository;
    private final BookJpaMapper mapper;

    public ReadBookAdapter(BookRepository bookRepository, BookJpaMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Boolean existsByIsbn(String isbn) {
        return bookRepository.existsByIsbn(isbn);
    }
}
