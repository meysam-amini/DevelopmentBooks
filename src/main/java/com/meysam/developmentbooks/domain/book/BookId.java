package com.meysam.developmentbooks.domain.book;

import jakarta.annotation.Nullable;

import java.util.Objects;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOK_ID_NEGATIVE;

public record BookId(@Nullable Long value) {

    public BookId {
        if (Objects.nonNull(value) && value < 0) throw new IllegalArgumentException(BOOK_ID_NEGATIVE);
    }
}
