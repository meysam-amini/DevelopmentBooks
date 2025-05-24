package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.domain.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.meysam.developmentbooks.utils.BookSamples.createBook;
import static com.meysam.developmentbooks.utils.BookSamples.getSampleBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddNewBookServiceTest {


    @Mock
    private WriteBookPort writeBookPort;

    @Mock
    private ReadBookPort readBookPort;

    @InjectMocks
    private AddNewBookService addNewBookService;


    @Test
    void throwsWhenBookObjectIsNotValid() {

        assertThrows(IllegalArgumentException.class,
                () -> addNewBookService.saveBook(createBook(null,null,null,null,0)));

        Mockito.verify(writeBookPort, Mockito.never()).save(any());
        Mockito.verify(readBookPort, Mockito.never()).existsByIsbn(any());

    }


    @Test
    void throwsWhenBookAlreadyExists() {
        Book book = getSampleBook(1);

        Mockito.when(readBookPort.existsByIsbn(book.getIsbn().value())).thenReturn(true);

        assertThrows(IllegalArgumentException.class,()-> addNewBookService.saveBook(book));

        Mockito.verify(writeBookPort, Mockito.never()).save(any());
        Mockito.verify(readBookPort, Mockito.times(1)).existsByIsbn(any());

    }


    @Test
    void shouldSaveValidBookAndNonDuplicateByIsbn() {
        Book validBook = getSampleBook(1);

        Mockito.when(writeBookPort.save(validBook)).thenReturn(validBook);
        Mockito.when(readBookPort.existsByIsbn(validBook.getIsbn().value())).thenReturn(false);

        Book savedBook = addNewBookService.saveBook(validBook);
        assertEquals(validBook, savedBook);

        Mockito.verify(writeBookPort, Mockito.times(1)).save(any());
        Mockito.verify(readBookPort, Mockito.times(1)).existsByIsbn(any());

    }

}