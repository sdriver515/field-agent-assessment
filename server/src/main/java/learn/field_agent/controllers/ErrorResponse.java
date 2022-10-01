package learn.field_agent.controllers;

import learn.field_agent.domain.Result;
import learn.field_agent.domain.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ErrorResponse {
    private  final LocalDateTime timeStamp = LocalDateTime.now();
    private final String message;
    public String getMessage() {
        return message;
    }
    public ErrorResponse(String message){
        this.message = message;
    }

    //this below is just a static method - do not get rid of it
    public static <T> ResponseEntity<Object> build(Result<T> result) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;//returning an internal server error
        if (result.getType() == null || result.getType() == ResultType.INVALID) {//but if the type is null...
            status = HttpStatus.BAD_REQUEST;
        } else if (result.getType() == ResultType.NOT_FOUND) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result.getMessages(), status);
    }//build

}//end
