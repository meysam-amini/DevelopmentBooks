package com.meysam.developmentbooks.adapters.book.in.web;

public record BookDto(
        Long id,
        String isbn,
        String title,
        String author,
        int publicationYear
) {

}