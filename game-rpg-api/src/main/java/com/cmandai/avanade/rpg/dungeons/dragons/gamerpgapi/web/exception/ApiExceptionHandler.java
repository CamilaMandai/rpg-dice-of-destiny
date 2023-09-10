package com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.web.exception;

import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.CharacterUniqueViolationException;
import com.cmandai.avanade.rpg.dungeons.dragons.gamerpgapi.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            HttpServletRequest request,
            BindingResult result
            ) {
        log.error("Api error - ", exception);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Invalid Field(s)",
                        result));
    }

    @ExceptionHandler(CharacterUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> UniqueViolationException(
            RuntimeException exception,
            HttpServletRequest request
    ) {
        log.error("Api error - ", exception);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.CONFLICT,
                        exception.getMessage()
                        ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(
            RuntimeException exception,
            HttpServletRequest request
    ) {
        log.error("Api error - ", exception);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.NOT_FOUND,
                        exception.getMessage()
                ));
    }
}
