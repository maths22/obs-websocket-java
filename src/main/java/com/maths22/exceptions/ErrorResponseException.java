package com.maths22.exceptions;

/**
* Thrown when the server responds with an error
*/
public class ErrorResponseException  extends RuntimeException
{
    /**
    * Constructor
    * 
    *  @param message The error message
    */
    public ErrorResponseException(String message) {
        super(message);
    }

}


