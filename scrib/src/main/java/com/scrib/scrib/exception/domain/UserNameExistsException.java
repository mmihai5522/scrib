package com.scrib.scrib.exception.domain;

public class UserNameExistsException extends Exception{
    public UserNameExistsException(String message) {
        super(message);
    }
}
