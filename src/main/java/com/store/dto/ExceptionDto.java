package com.store.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ExceptionDto {

    private String message;
    private HttpStatus status;

}
