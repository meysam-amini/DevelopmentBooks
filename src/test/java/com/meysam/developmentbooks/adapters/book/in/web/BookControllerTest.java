package com.meysam.developmentbooks.adapters.book.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meysam.developmentbooks.adapters.common.ApiResponse;
import com.meysam.developmentbooks.application.book.ports.in.command.CreateBookCommand;
import com.meysam.developmentbooks.domain.book.Book;
import com.meysam.developmentbooks.utils.BookSamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private WriteBookApiAdapter writeBookApiAdapter;
    @Mock
    private ReadBookApiAdapter readBookApiAdapter;
    @Mock
    private BookWebMapper bookWebMapper;

    @InjectMocks
    private BookController bookController;

    private String requestJson;
    private CreateBookCommand request;
    private Book book;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        request = new CreateBookCommand("9991234567", "Test Author", "Test Title", 2023);
        requestJson = objectMapper.writeValueAsString(request);
        book = BookSamples.getSampleBook(0);


    }

    private static final String API_PATH = "/api/v1/books";

    @Test
    void createBookShouldReturn200_whenApiExists() throws Exception {

        MvcResult result = mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        int status = result.getResponse().getStatus();
        String responseJson = result.getResponse().getContentAsString();

        assertThat(status)
                .withFailMessage("Expected 2xx status on call to %s but got %s. Response: %s", API_PATH, status, responseJson)
                .isBetween(200, 299);

    }


    @Test
    void createBookShouldReturnApiResponseObject() throws Exception {
        MvcResult result = mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        ApiResponse apiResponse = parseApiResponse(responseJson);

        assertThat(apiResponse)
                .withFailMessage("Response from %s was not deserialized to ApiResponse object: %s", API_PATH, responseJson)
                .isNotNull();
    }

    @Test
    void createBookShouldReturnNonNullMessageAndCode() throws Exception {
        MvcResult result = mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        ApiResponse apiResponse = parseApiResponse(responseJson);

        assertThat(apiResponse.msg())
                .withFailMessage("Response message from %s was null. Full response: %s", API_PATH, responseJson)
                .isNotNull()
                .isNotEmpty();

        assertThat(apiResponse.code())
                .withFailMessage("Response code from %s was null. Full response: %s", API_PATH, responseJson)
                .isNotNull();
    }


    @Test
    void createBookShouldCallWriteBookApiAdapter() throws Exception {
        MvcResult result = mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();
        try {
            verify(writeBookApiAdapter).save(any());
        } catch (MockitoAssertionError e) {
            String responseBody = result.getResponse().getContentAsString();
            throw new AssertionError(
                    String.format("Expected BookUseCases.createBook() to be called but it wasn't. API Response: %s",
                            responseBody),
                    e
            );
        }

    }


    @Test
    void createBookShouldAcceptCreateBookRequestAndReturnApiResponse() throws Exception {

        String requestJson = objectMapper.writeValueAsString(request);

        when(writeBookApiAdapter.save(any(CreateBookCommand.class))).thenReturn(book);

        MvcResult result = mockMvc.perform(post(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andReturn();

        int status = result.getResponse().getStatus();
        String responseJson = result.getResponse().getContentAsString();

        assertThat(status)
                .withFailMessage("Expected 2xx status for POST %s, got %s. Response: %s", API_PATH, status, responseJson)
                .isBetween(200, 299);

        ApiResponse apiResponse = parseApiResponse(responseJson);
        assertThat(apiResponse)
                .withFailMessage("Response from %s is not ApiResponse: %s", API_PATH, responseJson)
                .isNotNull();

        verify(writeBookApiAdapter).save(any(CreateBookCommand.class));
    }


    private ApiResponse parseApiResponse(String json) throws AssertionError {
        try {
            return objectMapper.readValue(json, ApiResponse.class);
        } catch (Exception ex) {
            throw new AssertionError(
                    String.format("Failed to parse response from %s as ApiResponse. Response: %s", API_PATH, json),
                    ex
            );
        }
    }

}
