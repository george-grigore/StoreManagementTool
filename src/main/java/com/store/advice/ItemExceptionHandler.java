package com.store.advice;

import com.store.dto.ExceptionDto;
import com.store.exception.ItemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemExceptionHandler.class);

    @ExceptionHandler(ItemException.class)
    public ResponseEntity<ExceptionDto> handleItemException(ItemException exception) {

        LOGGER.error(exception.getMessage());

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ExceptionDto exceptionDto = ExceptionDto.builder()
                .message(exception.getMessage())
                .status(httpStatus)
                .build();
        return new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
