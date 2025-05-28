package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.domain.book.*;
import com.meysam.developmentbooks.infrastructure.annotations.Mapper;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOK_IS_NULL;
import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.CREATE_BOOK_COMMAND_IS_NULL;

@Mapper
public class BookWebMapper {

    public Book toDomain(CreateBookCommand createBookCommand) {
        if (createBookCommand == null)
            throw new IllegalArgumentException(CREATE_BOOK_COMMAND_IS_NULL);

        return new Book(
                null,
                new BookIsbn(createBookCommand.isbn()),
                new Title(createBookCommand.title()),
                new Author(createBookCommand.author()),
                new PublicationYear(createBookCommand.publicationYear())
        );
    }

    public BookDto toDto(Book book) {

        if (book == null)
            throw new IllegalArgumentException(BOOK_IS_NULL);

        return new BookDto(book.getId().value(),
                book.getIsbn().value(),
                book.getTitle().value(),
                book.getAuthor().value(),
                book.getPublicationYear().value());

    }


    public BookSearchCriteria queryObjectToCriteria(FindBooksQuery findBooksQuery) {

        if (findBooksQuery == null)
            return BookSearchCriteria.empty();

        return new BookSearchCriteria(findBooksQuery.author()
                , findBooksQuery.title()
                , findBooksQuery.publicationYear()
                , findBooksQuery.pageSize()
                , findBooksQuery.pageNumber()
        );
    }
}
