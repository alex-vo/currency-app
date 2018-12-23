package me.bill.controller;

import lombok.extern.slf4j.Slf4j;
import me.bill.dto.ErrorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handle(ResponseStatusException e) {
        log.error("Failed to process request", e);
        return new ResponseEntity<>(new ErrorResponseDTO(e.getStatus().value(), e.getReason()), e.getStatus());
    }
}
