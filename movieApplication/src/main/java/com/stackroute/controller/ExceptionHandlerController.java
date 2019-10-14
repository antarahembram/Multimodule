package com.stackroute.controller;

import com.stackroute.exception.MovieAlreadyExistsException;
import com.stackroute.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> conflict(Exception e)
    {
        ResponseEntity responseEntity=new ResponseEntity<String>("Global exception: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return  responseEntity;
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<?> handleMovieNotFoundException(MovieNotFoundException e)
    {
        ResponseEntity responseEntity=new ResponseEntity<String>(" Movie Not Found exception: "+e.getMessage(), HttpStatus.NOT_FOUND);
        return  responseEntity;
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<?> handleMovieAlraedyFoundException(MovieAlreadyExistsException e)
    {
        ResponseEntity responseEntity=new ResponseEntity<String>(" Movie Not Found exception: "+e.getMessage(), HttpStatus.CONFLICT);
        return  responseEntity;
    }
}
