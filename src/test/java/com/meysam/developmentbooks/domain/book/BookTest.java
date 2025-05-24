package com.meysam.developmentbooks.domain.book;

import com.meysam.developmentbooks.domain.book.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void createsBookWithValidParameters() {
        BookId id = new BookId(null);
        Title title = new Title("Clean Code");
        Author author = new Author("Robert Martin");
        PublicationYear year = new PublicationYear(2008);

        Book book = new Book(id, title, author, year);

        assertEquals(id, book.getId());
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(year, book.getPublicationYear());
    }

    @Test
    void throwsWhenAnyParameterIsNull() {
        BookId id = new BookId(null);
        Title title = new Title("Clean Code");
        Author author = new Author("Robert Martin");
        PublicationYear year = new PublicationYear(2008);

        assertThrows(IllegalArgumentException.class, () -> new Book(null, title, author, year));
        assertThrows(IllegalArgumentException.class, () -> new Book(id, null, author, year));
        assertThrows(IllegalArgumentException.class, () -> new Book(id, title, null, year));
        assertThrows(IllegalArgumentException.class, () -> new Book(id, title, author, null));
    }

}