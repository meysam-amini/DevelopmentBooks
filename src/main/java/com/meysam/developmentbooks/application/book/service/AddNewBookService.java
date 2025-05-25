package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.application.book.usecase.AddNewBookUseCase;
import com.meysam.developmentbooks.domain.book.Book;

public class AddNewBookService implements AddNewBookUseCase {

    private final WriteBookPort writeBookPort;
    private final ReadBookPort readBookPort;

    public AddNewBookService(WriteBookPort writeBookPort, ReadBookPort readBookPort) {
        this.writeBookPort = writeBookPort;
        this.readBookPort = readBookPort;
    }


    @Override
    public Book saveBook(Book book) {

        if(book == null){
            throw new IllegalArgumentException("Book object is null!");
        }

        if(readBookPort.existsByIsbn(book.getIsbn().value())){
            throw new IllegalArgumentException("Book already exists by ISBN!");
        }

        return writeBookPort.save(book);
    }
}
