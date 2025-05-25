package com.meysam.developmentbooks.domain.book;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.*;

public record Title(String value) {

    public Title{
        if (value == null) throw new IllegalArgumentException(TITLE_CANNOT_BE_NULL_OR_BLANK);
        if (value.isBlank()) throw new IllegalArgumentException(TITLE_CANNOT_BE_NULL_OR_BLANK);
        if (value.length() > 100) throw new IllegalArgumentException(TITLE_TOO_LONG);
    }
}
