package net.javaSpring.springBoot.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.javaSpring.springBoot.exception.custom.CustomBadRequest;
import net.javaSpring.springBoot.exception.custom.CustomNotFound;
import net.javaSpring.springBoot.model.dto.ErrorMessage;

@ControllerAdvice
public class CustomExceptionHandler {
    private ErrorMessage<Object> errorMessage;

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception e){
        errorMessage = new ErrorMessage<Object>(HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now(), e.getMessage(), null);
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(value = CustomNotFound.class)
    public ResponseEntity<Object> handleNotFound(CustomNotFound e){
        errorMessage = new ErrorMessage<Object>(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage(), null);
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(value = CustomBadRequest.class)
    public ResponseEntity<Object> handleBadRequest(CustomBadRequest e){
        errorMessage = new ErrorMessage<Object>(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage(), null);
        return ResponseEntity.status(errorMessage.getStatus()).body(errorMessage);
    }

}
