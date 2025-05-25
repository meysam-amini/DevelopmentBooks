package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.adapters.book.out.persistence.Entity.BookEntity;
import com.meysam.developmentbooks.application.book.ports.out.persistence.query.BookSearchCriteria;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReadBookAdapterTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookJpaMapper mapper;

    @InjectMocks
    private ReadBookAdapter readBookAdapter;

    private Book book;
    private BookEntity bookEntity;

    @BeforeEach
    void setUp() {
        book = BookSamples.getSampleBook(0);
        bookEntity = new BookEntity();
    }

    @Test
    void shouldReturnBookWhenExists() {
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        when(mapper.toDomain(bookEntity)).thenReturn(book);

        List<Book> result = readBookAdapter.findAll(BookSearchCriteria.empty());

        assertThat(result).isNotEmpty().containsExactly(book);
        verify(bookRepository, times(1)).findAll();
        verify(mapper, times(1)).toDomain(bookEntity);
    }

    @Test
    void shouldReturnEmptyListWhenBooksAreNotPersisted() {
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        List<Book> result = readBookAdapter.findAll(BookSearchCriteria.empty());

        assertThat(result).isEmpty();
        verify(bookRepository, times(1)).findAll();
        verify(mapper, never()).toDomain(any());
    }

    @Test
    void shouldReturnTrueWhenBookExistsByIsbn() {
        when(bookRepository.existsByIsbn(book.getIsbn().value())).thenReturn(true);

        boolean exists = readBookAdapter.existsByIsbn(book.getIsbn().value());

        assertThat(exists).isTrue();
        verify(bookRepository, times(1)).existsByIsbn(book.getIsbn().value());
    }

    @Test
    void shouldReturnFalseWhenBookDoesNotExistByIsbn() {
        when(bookRepository.existsByIsbn(book.getIsbn().value())).thenReturn(false);

        boolean exists = readBookAdapter.existsByIsbn(book.getIsbn().value());

        assertThat(exists).isFalse();
        verify(bookRepository, times(1)).existsByIsbn(book.getIsbn().value());
    }
}
