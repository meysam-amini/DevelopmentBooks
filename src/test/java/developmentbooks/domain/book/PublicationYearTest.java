package developmentbooks.domain.book;

import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PublicationYearTest {

    @Test
    void createsValidPublicationYear() {
        PublicationYear year = new PublicationYear(2008);
        assertEquals(2008, year.value());
    }

    @Test
    void throwsWhenYearIsTooEarlyOrInFuture() {
        assertThrows(IllegalArgumentException.class, () -> new PublicationYear(1799));
        int futureYear = Year.now().getValue() + 1;
        assertThrows(IllegalArgumentException.class, () -> new PublicationYear(futureYear));

    }

}