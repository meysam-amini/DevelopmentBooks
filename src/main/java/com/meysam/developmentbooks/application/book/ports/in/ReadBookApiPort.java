package com.meysam.developmentbooks.application.book.ports.in;

import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public interface ReadBookApiPort {

    List<Book> findAllBooks(FindBooksQuery findBooksQuery);

    boolean existsByIsbn(String isbn);
}
