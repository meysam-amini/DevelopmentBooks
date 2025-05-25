package com.meysam.developmentbooks.adapters.book.out.persistence;

import com.meysam.developmentbooks.adapters.book.out.persistence.Entity.BookEntity;
import com.meysam.developmentbooks.domain.book.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.meysam.developmentbooks.utils.BookSamples.createBook;
import static com.meysam.developmentbooks.utils.BookSamples.getSampleBook;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WriteBookAdapterTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookJpaMapper bookJpaMapper;

    @InjectMocks
    private WriteBookAdapter writeBookAdapter;


    @Test
    void shouldSaveValidBook() {
        Book book = getSampleBook(1);
        BookEntity entity = new BookEntity();
        BookEntity saved = new BookEntity();

        when(bookJpaMapper.toJpaEntity(book)).thenReturn(entity);
        when(bookRepository.save(entity)).thenReturn(saved);
        when(bookJpaMapper.toDomain(saved)).thenReturn(book);

        Book result = writeBookAdapter.save(book);

        assertThat(result).isEqualTo(book);
        verify(bookRepository, times(1)).save(entity);
        verify(bookJpaMapper, times(1)).toDomain(saved);
    }


    @Test
    void ThrowsWhenTryToSaveInvalidBook() {

        assertThrows(IllegalArgumentException.class, () -> writeBookAdapter.save(createBook(null, null, null, null, 0)));

        verify(bookRepository, never()).save(any());
        verify(bookJpaMapper, never()).toJpaEntity(any());
    }

}
