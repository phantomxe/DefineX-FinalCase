package com.patika.akbankservice.exceptions;
 
import com.patika.akbankservice.exceptions.dto.ExceptionResponse;
import com.patika.akbankservice.exceptions.dto.KafkaLog;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
 
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;  

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllException(Exception exception) {
        log.error("exception occurred. {0}", exception.getCause());

        kafkaTemplate.send("patika.logs", new KafkaLog("exception-akbank-service", ExceptionUtils.getFullStackTrace(exception) ));
        log.info("Exception sent to Kafka");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(prepareExceptionResponse(exception, HttpStatus.BAD_REQUEST));
    }

    private ExceptionResponse prepareExceptionResponse(Exception exception, HttpStatus httpStatus) {
        return ExceptionResponse.builder()
                .message(exception.getMessage())
                .httpStatus(httpStatus)
                .build();
    }

}
