package com.meysam.developmentbooks.domain.book;

import java.time.Year;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.YEAR_IN_FUTURE;
import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.YEAR_TOO_EARLY;

public record PublicationYear(int value) {

    public PublicationYear {
        if (value < 1800) throw new IllegalArgumentException(YEAR_TOO_EARLY);
        if (value > Year.now().getValue()) throw new IllegalArgumentException(YEAR_IN_FUTURE);
    }
}
