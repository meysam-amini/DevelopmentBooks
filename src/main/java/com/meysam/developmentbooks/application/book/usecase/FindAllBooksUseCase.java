package com.meysam.developmentbooks.application.book.usecase;

import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public interface FindAllBooksUseCase {

    List<Book> findAllBooks(BookSearchCriteria bookSearchCriteria);
}
