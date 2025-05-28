package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
import com.meysam.developmentbooks.application.book.usecase.BookExistsByIsbnUseCase;
import com.meysam.developmentbooks.application.book.usecase.FindAllBooksUseCase;
import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.utils.BookSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadBookApiAdapterTest {

    @Mock
    private FindAllBooksUseCase findAllBooksUseCase;

    @Mock
    private BookExistsByIsbnUseCase bookExistsByIsbnUseCase;

    @Mock
    private BookWebMapper bookWebMapper;

    @InjectMocks
    private ReadBookApiAdapter readBookApiAdapter;

    private FindBooksQuery validQuery;
    private BookSearchCriteria criteria;
    private Book book;

    @BeforeEach
    void setUp() {
        validQuery = new FindBooksQuery("Robert Martin", "Clean Code", 2008, 0, 10);
        criteria = new BookSearchCriteria("Robert Martin", "Clean Code", 2008, 0, 10);
        book = BookSamples.getSampleBook(0);
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoPersistedBooks() {
        when(bookWebMapper.queryObjectToCriteria(validQuery)).thenReturn(criteria);
        when(findAllBooksUseCase.findAllBooks(criteria)).thenReturn(Collections.emptyList());

        List<Book> result = readBookApiAdapter.findAllBooks(validQuery);

        assertThat(result).isEmpty();
        verify(bookWebMapper).queryObjectToCriteria(validQuery);
        verify(findAllBooksUseCase).findAllBooks(criteria);
    }

    @Test
    void shouldReturnListOfDesiredBooks() {
        when(bookWebMapper.queryObjectToCriteria(validQuery)).thenReturn(criteria);
        when(findAllBooksUseCase.findAllBooks(criteria)).thenReturn(List.of(book));

        List<Book> result = readBookApiAdapter.findAllBooks(validQuery);

        assertThat(result).containsExactly(book);
        verify(bookWebMapper).queryObjectToCriteria(validQuery);
        verify(findAllBooksUseCase).findAllBooks(criteria);
    }


    @Test
    void shouldReturnTrueWhenBookExistsByIsbn() {
        String isbn = book.getIsbn().value();
        when(bookExistsByIsbnUseCase.existsByIsbn(isbn)).thenReturn(true);

        boolean exists = readBookApiAdapter.existsByIsbn(isbn);

        assertThat(exists).isTrue();
        verify(bookExistsByIsbnUseCase).existsByIsbn(isbn);
    }

    @Test
    void shouldReturnFalseWhenBookNotExistsByIsbn() {
        String isbn = book.getIsbn().value();
        when(bookExistsByIsbnUseCase.existsByIsbn(isbn)).thenReturn(false);

        boolean exists = readBookApiAdapter.existsByIsbn(isbn);

        assertThat(exists).isFalse();
        verify(bookExistsByIsbnUseCase).existsByIsbn(isbn);
    }
}
