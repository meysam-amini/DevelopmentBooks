package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.domain.book.Book;

public class WriteBookAdapter implements WriteBookPort {


    @Override
    public Book save(Book book) {
        return null;
    }
}
