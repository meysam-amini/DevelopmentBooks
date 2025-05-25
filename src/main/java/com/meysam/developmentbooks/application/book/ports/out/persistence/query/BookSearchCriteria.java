package com.meysam.developmentbooks.application.book.ports.out.persistence.query;

public record BookSearchCriteria(String author, String title, Integer publicationYear, Integer pageNumber,
                                 Integer pageSize) {

    public boolean hasCriteria() {
        return title != null || author != null || publicationYear != null || pageNumber != null || pageSize != null;
    }

    public static BookSearchCriteria empty() {
        return new BookSearchCriteria(null, null, null,null,null);
    }
}