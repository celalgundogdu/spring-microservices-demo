package com.celalgundogdu.organizationservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleOrganizationNotFoundException(OrganizationNotFoundException exception,
                                                                        WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());
        ApiError error = new ApiError(
          LocalDateTime.now(),
          "Organization Not Found",
                webRequest.getDescription(false),
                details
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest webRequest) {
        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());
        ApiError error = new ApiError(
                LocalDateTime.now(),
                "Malformed JSON request" ,
                webRequest.getDescription(false),
                details
        );
        return new ResponseEntity<>(error,  HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest webRequest) {
        List<String> details = new ArrayList<String>();
        details = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError error = new ApiError(
                LocalDateTime.now(),
                "Validation Errors" ,
                webRequest.getDescription(false),
                details);

        return new ResponseEntity<>(error,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(Exception exception, WebRequest webRequest) {

        List<String> details = new ArrayList<String>();
        details.add(exception.getMessage());

        ApiError error = new ApiError(
                LocalDateTime.now(),
                "Constraint Violations" ,
                webRequest.getDescription(false),
                details);

        return new ResponseEntity<>(error,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(Exception exception, WebRequest webRequest) {
        List<String> details = new ArrayList<String>();
        details.add(exception.getLocalizedMessage());
        ApiError error = new ApiError(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                details
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
