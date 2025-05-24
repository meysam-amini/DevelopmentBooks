package developmentbooks.domain.book;

import jakarta.annotation.Nullable;

import java.util.Objects;

public record BookId(@Nullable Long value) {

    public BookId {
        if (Objects.nonNull(value) && value < 0) throw new IllegalArgumentException("BookId can't be negative!");
    }
}
