package com.student.details.exceptions;

public class EmailRegisteredException extends RuntimeException {

    public EmailRegisteredException() {
        super("Email Already Registered..!!");
    }

}
