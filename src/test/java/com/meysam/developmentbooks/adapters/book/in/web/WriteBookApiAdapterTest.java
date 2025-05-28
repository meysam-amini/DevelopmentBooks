package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.application.book.usecase.AddNewBookUseCase;
import com.meysam.developmentbooks.domain.book.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.meysam.developmentbooks.utils.BookSamples.getSampleBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WriteBookApiAdapterTest {

    @Mock
    private AddNewBookUseCase addNewBookUseCase;

    @Mock
    private BookWebMapper bookWebMapper;

    @InjectMocks
    private WriteBookApiAdapter writeBookApiAdapter;

    private Book book;
    private CreateBookCommand command;

    @BeforeEach
    void setUp() {
        book = getSampleBook(0);
        command = new CreateBookCommand(
                book.getIsbn().value(),
                book.getTitle().value(),
                book.getAuthor().value(),
                book.getPublicationYear().value()
        );
    }

    @Test
    void shouldSaveBookAndReturnBookWhenInputIsValidCreateBookCommandObject() {
        when(bookWebMapper.toDomain(command)).thenReturn(book);
        when(addNewBookUseCase.saveBook(book)).thenReturn(book);

        Book result = writeBookApiAdapter.save(command);

        assertThat(result).isEqualTo(book);
        verify(bookWebMapper, times(1)).toDomain(command);
        verify(addNewBookUseCase, times(1)).saveBook(book);
    }

    @Test
    void throwsWhenInputIsNotValidCreateBookCommandObject() {

        assertThrows(IllegalArgumentException.class, () -> writeBookApiAdapter.save( new CreateBookCommand("", "", "", 0)));

        verify(bookWebMapper, never()).toDomain(any());
        verify(addNewBookUseCase, never()).saveBook(any());
    }
}
