package com.akash.recruitment.excepion;

public class CustomException extends RuntimeException {

    public CustomException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
