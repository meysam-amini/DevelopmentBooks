package developmentbooks.domain.book;

public class Book {

    private final BookId id;
    private final Title title;
    private final Author author;
    private final PublicationYear publicationYear;


    public Book(BookId id, Title title, Author author, PublicationYear publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
