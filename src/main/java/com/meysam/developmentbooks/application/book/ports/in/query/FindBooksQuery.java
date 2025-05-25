package com.meysam.developmentbooks.application.book.ports.in.query;

public record FindBooksQuery(
        String author, String title, Integer publicationYear, Integer pageNumber,
        Integer pageSize
) {

    public boolean hasParams() {
        return title != null || author != null || publicationYear != null || pageNumber != null || pageSize != null;
    }

    public static FindBooksQuery empty() {
        return new FindBooksQuery(null, null, null,null,null);
    }


}
