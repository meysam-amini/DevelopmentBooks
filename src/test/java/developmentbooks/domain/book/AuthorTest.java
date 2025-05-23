package developmentbooks.domain.book;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorTest {

    @Test
    void createsValidAuthor() {
        Author author = new Author("Robert Martin");
        assertEquals("Robert Martin", author.value());
    }

    @Test
    void throwsWhenAuthorIsNullOrBlank() {
        assertThrows(IllegalArgumentException.class, () -> new Author(null));
        assertThrows(IllegalArgumentException.class, () -> new Author("   "));

    }

}