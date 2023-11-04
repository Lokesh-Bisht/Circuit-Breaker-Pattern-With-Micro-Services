package dev.lokeshbisht.CatalogService.controller;

import dev.lokeshbisht.CatalogService.constants.ErrorCode;
import dev.lokeshbisht.CatalogService.dto.ErrorResponseDto;
import dev.lokeshbisht.CatalogService.exceptions.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(Exception ex) {
        log.error("ProductNotFoundException: {}", ex.getMessage());
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(ErrorCode.PRODUCT_NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
