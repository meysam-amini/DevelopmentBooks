package com.meysam.developmentbooks.application.book.ports.in.command;

import java.time.Year;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.*;

public record CreateBookCommand(
        String isbn,
        String author,
        String title,
        Integer publicationYear
) {
    public CreateBookCommand {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException(BOOK_ISBN_NULL_OR_BLANK);
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException(AUTHOR_CANNOT_BE_BLANK_OR_NULL);
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(TITLE_CANNOT_BE_NULL_OR_BLANK);
        }

        if (publicationYear == null) {
            throw new IllegalArgumentException(PUBLICATION_YEAR_NULL);
        }

        if (publicationYear < 1800) {
            throw new IllegalArgumentException(YEAR_TOO_EARLY);
        }

        if (publicationYear > Year.now().getValue()) {
            throw new IllegalArgumentException(YEAR_IN_FUTURE);
        }

    }
}
