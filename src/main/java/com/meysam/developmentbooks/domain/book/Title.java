package com.meysam.developmentbooks.domain.book;

public record Title(String value) {

    public Title{
        if (value == null) throw new IllegalArgumentException("Title cannot be null");
        if (value.isBlank()) throw new IllegalArgumentException("Title cannot be blank");
        if (value.length() > 100) throw new IllegalArgumentException("Title exceeds 100 chars");
    }
}
