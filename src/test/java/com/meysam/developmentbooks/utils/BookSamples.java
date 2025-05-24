package com.meysam.developmentbooks.utils;

import com.meysam.developmentbooks.domain.book.*;

import java.util.List;

public class BookSamples {

    private static final List<Book> SAMPLE_BOOKS = List.of(
            createBook(1L, "132350884", "Clean Code", "Robert Martin", 2008),
            createBook(2L, "201633610", "Design Patterns", "GoF", 1994),
            createBook(3L, "134494166", "Clean Architecture", "Robert Martin", 2017),
            createBook(4L, "321125217", "Domain-Driven Design", "Eric Evans", 2003)
    );

    public static Book createBook(Long id, String isbn, String title,
                                  String author, int year) {
        return new Book(
                new BookId(id),
                new BookIsbn(isbn),
                new Title(title),
                new Author(author),
                new PublicationYear(year)
        );
    }


    public static Book getSampleBook(int index) {
        return SAMPLE_BOOKS.get(index % SAMPLE_BOOKS.size());
    }


}