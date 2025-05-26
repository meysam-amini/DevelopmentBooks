package com.meysam.developmentbooks.adapters.book.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meysam.developmentbooks.adapters.book.out.persistence.BookRepository;
import com.meysam.developmentbooks.adapters.book.out.persistence.Entity.BookEntity;
import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.application.book.ports.in.query.FindBooksQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class BookControllerIntegrationTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private BookRepository bookRepository;

    private CreateBookCommand validCommand;

    private FindBooksQuery query = new FindBooksQuery(null, null, null, null,null);


    @BeforeEach
    void setUp() {
        validCommand = new CreateBookCommand("9991234567", "Test Author", "Test Title", 2023);
    }

    @Test
    void shouldCreateBook_whenValidCommandProvided() throws Exception {
        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(validCommand)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.isbn", is(validCommand.isbn())))
                .andExpect(jsonPath("$.data.author", is(validCommand.author())))
                .andExpect(jsonPath("$.data.title", is(validCommand.title())))
                .andExpect(jsonPath("$.data.publicationYear", is(validCommand.publicationYear())));
    }

    @Test
    void shouldReturn409_whenDuplicateIsbn() throws Exception {
        BookEntity existing = new BookEntity(null, validCommand.isbn(), validCommand.title(), validCommand.author(), validCommand.publicationYear());
        bookRepository.save(existing);

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validCommand)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.msg", containsStringIgnoringCase("already exists")));
    }

    @Test
    void shouldReturnBadRequest_whenInvalidCommand() throws Exception {

        String invalidJson = """
        {
          "isbn": "",
          "author": "",
          "title": "",
          "publicationYear": null
        }
        """;

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldFetchBooks_whenTheyExist() throws Exception {
        BookEntity b1 = new BookEntity(null, "111", "Title 1", "Author 1", 2001);
        BookEntity b2 = new BookEntity(null, "222", "Title 2", "Author 2", 2002);
        bookRepository.saveAll(List.of(b1, b2));

        mockMvc.perform(post("/api/v1/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(query)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[*].isbn", containsInAnyOrder("111", "222")));
    }

    @Test
    void shouldReturnEmpty_whenNoBooksExist() throws Exception {

        mockMvc.perform(post("/api/v1/books/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(query)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", hasSize(0)));
    }

}
