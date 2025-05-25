package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.adapters.book.in.web.dto.BookDto;
import com.meysam.developmentbooks.adapters.book.in.web.mapper.BookWebMapper;
import com.meysam.developmentbooks.adapters.common.ApiResponse;
import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import com.meysam.developmentbooks.domain.book.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                .body(new ApiResponse(201, dto, "Book created successfully"));
    }

    @PostMapping("/search")
    public ResponseEntity<ApiResponse> searchBooks(@RequestBody FindBooksQuery findBooksQuery) {
        List<Book> books = readBookApiAdapter.findAllBooks(findBooksQuery);
        List<BookDto> dtos = books.stream().map(bookWebMapper::toDto).toList();
        return ResponseEntity.ok(new ApiResponse(200, dtos, "Fetched books successfully"));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<ApiResponse> existsByIsbn(@PathVariable String isbn) {
        boolean exists = readBookApiAdapter.existsByIsbn(isbn);
        return ResponseEntity.ok(new ApiResponse(200, exists, "Book existence check complete"));
    }
}
