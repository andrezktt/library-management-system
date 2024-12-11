package org.example.exceptions;

public class BorrowDatabaseException extends BorrowServiceException {
    public BorrowDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
