package com.meysam.developmentbooks.domain.book;

import com.meysam.developmentbooks.domain.book.Title;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TitleTest {

    @Test
    void createsValidTitle() {
        Title title = new Title("Clean Code");
        assertEquals("Clean Code", title.value());
    }

    @Test
    void throwsWhenTitleIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Title(null));
        assertThrows(IllegalArgumentException.class, () -> new Title("   "));
    }


    @Test
    void throwsWhenTitleIsTooLong() {
        String longTitle = "a".repeat(101);
        assertThrows(IllegalArgumentException.class, () -> new Title(longTitle));
    }
}