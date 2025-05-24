package com.meysam.developmentbooks.application.book.ports.out.persistence;

import com.meysam.developmentbooks.domain.book.Book;

public interface WriteBookPort {

    Book save (Book book);
}
