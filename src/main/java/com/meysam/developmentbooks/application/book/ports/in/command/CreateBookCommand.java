package com.meysam.developmentbooks.application.book.ports.in.command;

public record CreateBookCommand(
        String isbn,
        String author,
        String title,
        Integer publicationYear
) {
    public CreateBookCommand {
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN must not be null or blank");
        }
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author must not be null or blank");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title must not be null or blank");
        }
        if (publicationYear == null || publicationYear <= 0) {
            throw new IllegalArgumentException("Publication year must be a positive number");
        }
    }
}
