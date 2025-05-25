package com.meysam.developmentbooks.domain.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookTest {

    @Test
    void createsBookWithValidParameters() {
        BookId id = new BookId(null);
        BookIsbn isbn = new BookIsbn("123");
        Title title = new Title("Clean Code");
        Author author = new Author("Robert Martin");
        PublicationYear year = new PublicationYear(2008);

        Book book = new Book(id, isbn, title, author, year);

        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(year, book.getPublicationYear());
    }

    @Test
    void throwsWhenAnyParameterIsNull() {
        BookId id = new BookId(null);
        BookIsbn isbn = new BookIsbn("123");
        Title title = new Title("Clean Code");
        Author author = new Author("Robert Martin");
        PublicationYear year = new PublicationYear(2008);

        assertThrows(IllegalArgumentException.class, () -> new Book(id, isbn, null, author, year));
        assertThrows(IllegalArgumentException.class, () -> new Book(id, isbn, title, null, year));
        assertThrows(IllegalArgumentException.class, () -> new Book(id, isbn, title, author, null));
    }

}