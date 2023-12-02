package br.com.luis.financial.controller;

import br.com.luis.financial.infra.exception.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ThreatExceptionController {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(IdNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }

    public static record ErrorResponse(String message) {}

    public record ValidationError(String field, String message) {
        public ValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
