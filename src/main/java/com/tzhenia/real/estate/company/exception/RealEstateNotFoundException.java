package com.tzhenia.real.estate.company.exception;

public class RealEstateNotFoundException extends RuntimeException {
    public RealEstateNotFoundException(String message) {
        super(message);
    }
}