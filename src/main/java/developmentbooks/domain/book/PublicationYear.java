package developmentbooks.domain.book;

import java.time.Year;

public record PublicationYear(int value) {

    public PublicationYear {
        if (value < 1800) throw new IllegalArgumentException("Year cannot be before 1800");
        if (value > Year.now().getValue()) throw new IllegalArgumentException("Year cannot be in future");
    }
}
