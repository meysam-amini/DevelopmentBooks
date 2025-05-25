package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.exception.DuplicateIsbnException;
import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.application.book.usecase.AddNewBookUseCase;
import com.meysam.developmentbooks.domain.book.Book;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOK_ALREADY_EXISTS_BY_ISBN;
import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOK_IS_NULL;

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
            throw new IllegalArgumentException(BOOK_IS_NULL);
        }

        if(readBookPort.existsByIsbn(book.getIsbn().value())){
            throw new DuplicateIsbnException(BOOK_ALREADY_EXISTS_BY_ISBN);
        }

        return writeBookPort.save(book);
    }
}
