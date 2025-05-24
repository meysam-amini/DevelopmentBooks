package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.WriteBookPort;
import com.meysam.developmentbooks.domain.book.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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
                () -> addNewBookService.saveBook(createTestBook(null)));

    }


    @Test
    void throwsWhenBookAlreadyExists() {
        fail("no impl");

    }


    @Test
    void shouldSaveValidBook() {
        Book validBook = createTestBook("OCP");

        Mockito.when(writeBookPort.save(validBook)).thenReturn(validBook);

        Book savedBook =addNewBookService.saveBook(validBook);
        assertEquals(validBook,savedBook);

        Mockito.verify(writeBookPort,Mockito.times(1)).save(any());

    }


    private Book createTestBook(String title) {
        return new Book(new BookId(1L)
                , new Title(title)
                , new Author("Bob")
                , new PublicationYear(1994));
    }
}