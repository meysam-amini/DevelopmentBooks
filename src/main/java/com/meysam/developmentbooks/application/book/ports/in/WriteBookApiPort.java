package com.meysam.developmentbooks.application.book.ports.in;

import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.domain.book.Book;

public interface WriteBookApiPort {

    Book save(CreateBookCommand createBookCommand);
}
