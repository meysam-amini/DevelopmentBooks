package com.meysam.developmentbooks.domain.book;

import java.util.Objects;

public record BookIsbn(String value) {

    public BookIsbn {
        if (Objects.isNull(value) || value.isBlank()) throw new IllegalArgumentException("BookIsbn can't be null or blank!");
    }
}
