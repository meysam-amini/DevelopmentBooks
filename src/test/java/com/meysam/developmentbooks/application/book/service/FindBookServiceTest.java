package com.meysam.developmentbooks.application.book.service;

import com.meysam.developmentbooks.application.book.ports.out.persistence.ReadBookPort;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.domain.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.meysam.developmentbooks.utils.BookSamples.getSampleBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindBookServiceTest {

    @Mock
    private ReadBookPort readBookPort;

    @InjectMocks
    private FindBookService findBookService;

    @Test
    void shouldReturnEmptyWhenNoBooksFound(){
        when(readBookPort.findAll(any())).thenReturn(new ArrayList<>());

        List<Book> books = findBookService.findAllBooks(BookSearchCriteria.empty());
        assertThat(books).isEmpty();

        verify(readBookPort,times(1)).findAll(any());
    }


    @Test
    void shouldReturnBooksWhenWeHavePersistedBooks(){
        Book book1 = getSampleBook(1);
        Book book2 = getSampleBook(2);

        when(readBookPort.findAll(any())).thenReturn(List.of(book1,book2));

        List<Book> bookList = findBookService.findAllBooks(BookSearchCriteria.empty());

        assertThat(bookList).hasSize(2).containsOnly(book1,book2);

        verify(readBookPort,times(1)).findAll(any());
    }

    @Test
    void shouldReturnTrueWhenBookExistsByIsbn(){
        fail();
    }

}