package com.huudan.TestInterView.services;

import org.example.MyBigNumber;
import org.springframework.stereotype.Service;

@Service
public class PlusTwoNumberService implements IPlusTwoNumberService {
    private MyBigNumber service;

    public PlusTwoNumberService(MyBigNumber service) {
        this.service = service;
    }

    public String plusTwoNumbers(String number1, String number2) {
        if (number1 == null || number1.trim().isEmpty())
            throw new IllegalArgumentException("First number cannot be null or empty");
        if (number2 == null || number2.trim().isEmpty())
            throw new IllegalArgumentException("Second number cannot be null or empty");
        if (!number1.matches("\\d+")) throw new IllegalArgumentException("First input is not a valid number");
        if (!number2.matches("\\d+")) throw new IllegalArgumentException("Second input is not a valid number");
        return service.sum(number1.trim(), number2.trim());
    }

}