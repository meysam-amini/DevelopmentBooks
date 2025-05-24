package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.domain.book.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
class AddNewBookServiceTest {


    @Mock
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
        fail("no impl");
    }


    private Book createTestBook(String title) {
        return new Book(new BookId(1L)
                , new Title(title)
                , new Author("Bob")
                , new PublicationYear(1994));
    }
}