package com.securityservice.advice;


import com.securityservice.dto.ErrResponse;
import com.securityservice.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class RestAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> userAlreadyExistsException(UserAlreadyExistsException exception){
        logger.info("error: {}", exception.getMessage());
        return new ResponseEntity<ErrResponse>(new ErrResponse("e01", exception.getMessage()),HttpStatus.FORBIDDEN);
    }
}
