package com.meysam.developmentbooks.domain.book;

public record Author(String value) {

    public Author{
        if (value == null) throw new IllegalArgumentException("Author cannot be null");
        if (value.isBlank()) throw new IllegalArgumentException("Author cannot be blank");
    }
}
