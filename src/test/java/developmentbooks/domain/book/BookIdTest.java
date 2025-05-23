package developmentbooks.domain.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookIdTest {

    @Test
    void createsValidBookId() {
        BookId bookId = new BookId(1L);
        assertEquals(1L, bookId.value());
    }

    @Test
    void bookIdNonNegative() {
        assertThrows(IllegalArgumentException.class, () -> new BookId(-1L));
    }

}