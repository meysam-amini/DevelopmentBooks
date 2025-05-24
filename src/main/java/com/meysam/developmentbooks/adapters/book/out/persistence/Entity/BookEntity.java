package com.meysam.developmentbooks.adapters.book.out.persistence.Entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Books")
public class BookEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String isbn;
    @NotNull
    private String author;
    @NotNull
    private String title;
    @NotNull
    private Integer publicationYear;
}
