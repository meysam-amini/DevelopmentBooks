package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.adapters.book.out.persistence.Entity.BookEntity;
import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.infrastructure.annotations.Adapter;

@Adapter
public class WriteBookAdapter implements WriteBookPort {

    private final BookRepository bookRepository;
    private final BookJpaMapper mapper;


    public WriteBookAdapter(BookRepository bookRepository, BookJpaMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }


    @Override
    public Book save(Book book) {
        BookEntity entity = mapper.toJpaEntity(book);
        BookEntity savedEntity = bookRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
