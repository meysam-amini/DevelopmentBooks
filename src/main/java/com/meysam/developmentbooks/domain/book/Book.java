package com.meysam.developmentbooks.domain.book;

public class Book {

    private final BookId id;
    private final Title title;
    private final Author author;
    private final PublicationYear publicationYear;


    public Book(BookId id, Title title, Author author, PublicationYear publicationYear) {

        if (id == null || title == null || author == null || publicationYear == null) {
            throw new IllegalArgumentException("All book parameters must be provided");
        }

        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public BookId getId() { return id; }
    public Title getTitle() { return title; }
    public Author getAuthor() { return author; }
    public PublicationYear getPublicationYear() { return publicationYear; }
}
