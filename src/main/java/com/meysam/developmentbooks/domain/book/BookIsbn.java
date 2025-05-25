package com.meysam.developmentbooks.domain.book;

import java.util.Objects;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOK_ISBN_NULL_OR_BLANK;

public record BookIsbn(String value) {

    public BookIsbn {
        if (Objects.isNull(value) || value.isBlank()) throw new IllegalArgumentException(BOOK_ISBN_NULL_OR_BLANK);
    }
}
