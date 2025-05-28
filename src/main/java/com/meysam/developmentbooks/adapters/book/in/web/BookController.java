package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.adapters.common.ApiResponse;
import com.meysam.developmentbooks.adapters.common.ResponseCodeConstants;
import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.domain.book.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOKS_FETCHED_SUCCESSFULLY;
import static com.meysam.developmentbooks.common.constants.MessageConstants.BookMsg.BOOK_CREATED_SUCCESSFULLY;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final WriteBookApiAdapter writeBookApiAdapter;
    private final ReadBookApiAdapter readBookApiAdapter;
    private final BookWebMapper bookWebMapper;

    public BookController(
            WriteBookApiAdapter writeBookApiAdapter,
            ReadBookApiAdapter readBookApiAdapter,
            BookWebMapper bookWebMapper
    ) {
        this.writeBookApiAdapter = writeBookApiAdapter;
        this.readBookApiAdapter = readBookApiAdapter;
        this.bookWebMapper = bookWebMapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createBook(@RequestBody @Valid CreateBookCommand createBookCommand) {
        Book saved = writeBookApiAdapter.save(createBookCommand);
        BookDto dto = bookWebMapper.toDto(saved);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(ResponseCodeConstants.CREATED, dto, BOOK_CREATED_SUCCESSFULLY));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse> searchBooks(@RequestBody FindBooksQuery findBooksQuery) {
        List<Book> books = readBookApiAdapter.findAllBooks(findBooksQuery);
        List<BookDto> dtos = books.stream().map(bookWebMapper::toDto).toList();
        return ResponseEntity.ok(ApiResponse.success(dtos, BOOKS_FETCHED_SUCCESSFULLY));
    }


}
