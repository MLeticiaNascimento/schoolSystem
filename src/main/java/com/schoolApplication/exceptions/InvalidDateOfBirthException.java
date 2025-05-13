package com.schoolApplication.exceptions;

import java.lang.Exception;

public class InvalidDateOfBirthException extends Exception{
    public InvalidDateOfBirthException (String message){
        super (message);
    }
}
