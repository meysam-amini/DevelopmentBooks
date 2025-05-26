package com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.dto;

import com.meysam.developmentbooks.domain.book.Book;

public record BookQuantityDto(
        Long id,
        String isbn,
        String title,
        String author,
        int publicationYear,
        int quantity
) {

    public static BookQuantityDto make(Book book,int quantity){
        return new BookQuantityDto(book.getId().value()
                ,book.getIsbn().value()
                ,book.getTitle().value()
                ,book.getAuthor().value()
                ,book.getPublicationYear().value()
                ,quantity);
    }
}
