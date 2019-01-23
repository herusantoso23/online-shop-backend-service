package com.herusantoso.tokonezia.exception;

import com.herusantoso.tokonezia.dto.CustomHttpServletResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomHttpServletResponseDTO> invalidInput(MethodArgumentNotValidException ex, HttpServletRequest requestToCache, HttpServletResponse responseToCache) {
        BindingResult result = ex.getBindingResult();
        CustomHttpServletResponseDTO response = new CustomHttpServletResponseDTO();
        response.setTimestamp(new Date());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("Bad Request");
        response.setMessage(result.getFieldError().getField() + " " + result.getFieldError().getDefaultMessage());
        response.setPath(requestToCache.getServletPath());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}