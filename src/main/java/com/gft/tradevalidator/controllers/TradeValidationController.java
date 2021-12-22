package com.gft.tradevalidator.controllers;

import com.gft.tradevalidator.model.BaseTradeRequest;
import com.gft.tradevalidator.model.SpotTradeRequest;
import com.gft.tradevalidator.model.TradeValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TradeValidationController {

    @PostMapping(value ="/validate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TradeValidationResponse validate(@Valid @RequestBody BaseTradeRequest requestBody) {
        System.out.println("request body= " + requestBody);
        TradeValidationResponse tradeValidationResponse = new TradeValidationResponse();
        tradeValidationResponse.setMessage("It's a valid trade");
        return tradeValidationResponse;
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    String handleConstraintViolationException(ConstraintViolationException e) {
        System.out.println("handleConstraintViolationException "+  e.getMessage());
        return "not valid due to validation error: " + e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            if(error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            }
            else{
                System.out.println("error is instance of "+ error.getDefaultMessage());
                errors.put("",error.getDefaultMessage());
            }
        });
        return errors;
    }
}
