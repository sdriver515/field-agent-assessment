package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.MediaTypeNotSupportedStatusException;

import java.io.IOException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //this is a general exception
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponse("Something went wrong!"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }//handleException

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("This is a data integrity violation exception."),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpClientErrorException.UnsupportedMediaType.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedMediaType(HttpClientErrorException.UnsupportedMediaType ex) {
        return new ResponseEntity<>(
                new ErrorResponse("This is an unsupported media type."),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchField(NoSuchFieldException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("No such field."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElement(NoSuchElementException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("No such element."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TypeNotPresentException.class)
    public ResponseEntity<ErrorResponse> handleTypeNotPresent(TypeNotPresentException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("No type present."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorResponse> handleJsonMappingIssue(JsonMappingException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Json mapping exception."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Null pointer exception."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("IO Exception."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    public ResponseEntity<ErrorResponse> handleMediaTypeException(HttpMediaTypeException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("Media Type Exception."),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE);//check this type
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex){
        return new ResponseEntity<>(
                new ErrorResponse("Http message not readable."),
                HttpStatus.BAD_REQUEST);
    }



}//end
