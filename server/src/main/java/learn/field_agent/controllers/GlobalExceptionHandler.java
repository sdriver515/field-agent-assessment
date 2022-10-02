package learn.field_agent.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //FIRST EXCEPTION HANDLER
    @ExceptionHandler(Exception.class) //this is a general exception
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        return new ResponseEntity<>(
                new ErrorResponse("Something went wrong!"),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }//handleException

    //SECOND EXCEPTION HANDLER
    //they want us to handle a data integrity violation exception
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(
                new ErrorResponse("This is a data integrity violation exception."),
                HttpStatus.CONFLICT);
    }

}//end
