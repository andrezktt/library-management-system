package org.example.exceptions;

public class BorrowNotFoundException extends BorrowServiceException {
    public BorrowNotFoundException(String message) {
        super(message);
    }
}
