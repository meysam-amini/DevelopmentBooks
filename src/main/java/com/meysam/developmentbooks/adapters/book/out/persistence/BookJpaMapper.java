package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.adapters.book.out.persistence.Entity.BookEntity;
import com.meysam.developmentbooks.domain.book.*;

class BookJpaMapper {

    BookEntity toJpaEntity(Book book) {
        return BookEntity.builder()
                .id(book.getId().value())
                .isbn(book.getIsbn().value())
                .title(book.getTitle().value())
                .author(book.getAuthor().value())
                .publicationYear(book.getPublicationYear().value())
                .build();
    }

    Book toDomain(BookEntity entity) {
        return new Book(
                new BookId(entity.getId()),
                new BookIsbn(entity.getIsbn()),
                new Title(entity.getTitle()),
                new Author(entity.getAuthor()),
                new PublicationYear(entity.getPublicationYear())
        );
    }
}
