package com.meysam.developmentbooks.domain.book;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.AUTHOR_CANNOT_BE_BLANK_OR_NULL;

public record Author(String value) {

    public Author{
        if (value == null) throw new IllegalArgumentException(AUTHOR_CANNOT_BE_BLANK_OR_NULL);
        if (value.isBlank()) throw new IllegalArgumentException(AUTHOR_CANNOT_BE_BLANK_OR_NULL);
    }
}
