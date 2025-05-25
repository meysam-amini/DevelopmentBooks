package com.meysam.developmentbooks.adapters.book.in.web;

import com.meysam.developmentbooks.adapters.common.ApiResponse;
import com.meysam.developmentbooks.adapters.common.ResponseCodeConstants;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.meysam.developmentbooks.common.constants.MessageConstants.Web.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(ResponseCodeConstants.BAD_REQUEST, null, INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(EntityExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse(ResponseCodeConstants.CONFLICT, null, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(ResponseCodeConstants.INTERNAL_ERROR, null, INTERNAL_SERVER_ERROR));
    }
}
