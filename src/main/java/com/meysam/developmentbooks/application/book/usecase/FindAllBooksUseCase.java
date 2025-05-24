package com.meysam.developmentbooks.application.book.usecase;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public interface FindAllBooksUseCase {

    List<Book> findAllBooks();
}
