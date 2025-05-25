package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.application.book.ports.in.ReadBookApiPort;
import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.application.book.usecase.BookExistsByIsbnUseCase;
import com.meysam.developmentbooks.application.book.usecase.FindAllBooksUseCase;
import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.adapters.book.in.web.mapper.BookWebMapper;
import com.meysam.developmentbooks.infrastructure.annotations.Adapter;

import java.util.List;

@Adapter
public class ReadBookApiAdapter implements ReadBookApiPort {

    private final FindAllBooksUseCase findAllBooksUseCase;
    private final BookExistsByIsbnUseCase bookExistsByIsbnUseCase;
    private final BookWebMapper bookWebMapper;

    public ReadBookApiAdapter(FindAllBooksUseCase findAllBooksUseCase, BookExistsByIsbnUseCase bookExistsByIsbnUseCase, BookWebMapper bookWebMapper) {
        this.findAllBooksUseCase = findAllBooksUseCase;
        this.bookExistsByIsbnUseCase = bookExistsByIsbnUseCase;
        this.bookWebMapper = bookWebMapper;
    }

    @Override
    public List<Book> findAllBooks(FindBooksQuery findBooksQuery) {
        BookSearchCriteria searchCriteria = bookWebMapper.queryObjectToCriteria(findBooksQuery);
        return findAllBooksUseCase.findAllBooks(searchCriteria);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookExistsByIsbnUseCase.existsByIsbn(isbn);
    }
}
