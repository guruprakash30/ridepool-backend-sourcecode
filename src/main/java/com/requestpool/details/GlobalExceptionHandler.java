package com.requestpool.details;


import com.requestpool.details.CustomExceptions.NoDataFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler{

    @ExceptionHandler
    public ResponseEntity<Object> noDataFoundExceptionHandler(NoDataFoundException ex){

        log.info("global handler hit successfully");

        log.info(ex.getMessage()+".......");

        Map<String,Object> responseBody = new LinkedHashMap<>();
        responseBody.put("Error_Message",ex.getMessage());
        responseBody.put("HTTP_STATUS_CODE", HttpStatus.NO_CONTENT.value());
        responseBody.put("TIMESTAMP",System.currentTimeMillis());

        return new ResponseEntity<>(responseBody,HttpStatus.NOT_FOUND);
    }
}
