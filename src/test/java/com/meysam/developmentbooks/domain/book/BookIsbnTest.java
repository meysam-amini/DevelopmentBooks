package com.meysam.developmentbooks.domain.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookIsbnTest {

    @Test
    void createsValidBookIsbn() {
        BookIsbn isbn = new BookIsbn("12345");
        assertEquals("12345", isbn.value());
    }

    @Test
    void throwsWhenIsBnIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new BookIsbn(null));
        assertThrows(IllegalArgumentException.class, () -> new BookIsbn("   "));

    }


}