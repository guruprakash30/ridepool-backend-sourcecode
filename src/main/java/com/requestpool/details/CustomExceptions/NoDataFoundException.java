package com.requestpool.details.CustomExceptions;

public class NoDataFoundException extends RuntimeException{

    public NoDataFoundException(String msg){
        super(msg);
    }
}
