package com.meysam.developmentbooks.adapters.book.in.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDto {
    private Long id;
    private String isbn;
    private String author;
    private String title;
    private Integer publicationYear;
}
