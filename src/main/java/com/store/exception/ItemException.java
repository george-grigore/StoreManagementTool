package com.store.exception;

public class ItemException extends RuntimeException {

    public ItemException(String message) {
        super(message);
    }

    public ItemException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
