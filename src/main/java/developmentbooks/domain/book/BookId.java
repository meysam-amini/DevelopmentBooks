package developmentbooks.domain.book;

public record BookId(Long value) {

    public BookId {
        if (value < 0) throw new IllegalArgumentException("BookId can't be negative!");
    }
}
