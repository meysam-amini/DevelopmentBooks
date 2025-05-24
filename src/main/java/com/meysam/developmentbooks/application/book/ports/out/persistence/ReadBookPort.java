package com.meysam.developmentbooks.application.book.ports.out.persistence;

import com.meysam.developmentbooks.domain.book.Book;

import java.util.List;

public interface ReadBookPort {

    List<Book> findAll();
}
