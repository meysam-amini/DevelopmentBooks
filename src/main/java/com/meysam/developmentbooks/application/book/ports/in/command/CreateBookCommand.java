package com.meysam.developmentbooks.application.book.ports.in.command;

public record CreateBookCommand(
        String isbn,
        String author,
        String title,
        Integer publicationYear
) {}
