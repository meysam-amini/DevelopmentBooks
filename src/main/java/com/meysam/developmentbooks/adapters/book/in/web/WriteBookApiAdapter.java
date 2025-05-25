package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.application.book.ports.in.WriteBookApiPort;
import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.domain.book.Book;

public class WriteBookApiAdapter implements WriteBookApiPort {

    @Override
    public Book save(CreateBookCommand createBookCommand) {
        return null;
    }
}
