package com.meysam.developmentbooks.domain.book;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.ALL_BOOK_PARAMS_REQUIRED;

public class Book {

    private final BookId id;
    private final BookIsbn isbn;
    private final Title title;
    private final Author author;
    private final PublicationYear publicationYear;


    public Book(BookId id, BookIsbn isbn, Title title, Author author, PublicationYear publicationYear) {

        if (title == null || author == null || publicationYear == null || isbn == null) {
            throw new IllegalArgumentException(ALL_BOOK_PARAMS_REQUIRED);
        }

        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public BookId getId() {
        return id;
    }

    public BookIsbn getIsbn(){
        return isbn;
    }

    public Title getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public PublicationYear getPublicationYear() {
        return publicationYear;
    }
}
