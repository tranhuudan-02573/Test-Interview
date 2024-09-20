package com.huudan.TestInterView.controllers;

import com.huudan.TestInterView.dtos.InputFromUser;
import com.huudan.TestInterView.dtos.ResultResponse;
import com.huudan.TestInterView.services.IPlusTwoNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlusTwoNumberController {

    private final IPlusTwoNumberService plusTwoNumberService;

    @Autowired
    public PlusTwoNumberController(IPlusTwoNumberService plusTwoNumberService) {
        this.plusTwoNumberService = plusTwoNumberService;
    }


    @PostMapping("/plus-two-number")
    public ResponseEntity<ResultResponse> plusTwoNumbers(@RequestBody InputFromUser numberInput) {
        return new ResponseEntity<>(new ResultResponse(plusTwoNumberService.plusTwoNumbers(numberInput.number1(), numberInput.number2())), HttpStatus.OK);
    }
}
