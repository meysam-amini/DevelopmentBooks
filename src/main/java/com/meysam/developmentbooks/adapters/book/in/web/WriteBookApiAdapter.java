package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.adapters.book.in.web.mapper.BookWebMapper;
import com.meysam.developmentbooks.application.book.ports.in.WriteBookApiPort;
import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.application.book.usecase.AddNewBookUseCase;
import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.infrastructure.annotations.Adapter;

@Adapter
public class WriteBookApiAdapter implements WriteBookApiPort {

    private final AddNewBookUseCase addNewBookUseCase;
    private final BookWebMapper bookWebMapper;

    public WriteBookApiAdapter(AddNewBookUseCase addNewBookUseCase, BookWebMapper bookWebMapper) {
        this.addNewBookUseCase = addNewBookUseCase;
        this.bookWebMapper = bookWebMapper;
    }

    @Override
    public Book save(CreateBookCommand createBookCommand) {
        Book savingBook = bookWebMapper.toDomain(createBookCommand);
        return addNewBookUseCase.saveBook(savingBook);
    }
}
