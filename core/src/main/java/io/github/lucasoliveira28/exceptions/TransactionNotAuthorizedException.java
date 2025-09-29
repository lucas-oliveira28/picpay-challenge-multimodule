package io.github.lucasoliveira28.exceptions;

public class TransactionNotAuthorizedException extends RuntimeException {
    public TransactionNotAuthorizedException(String message) {
        super(message);
    }
}
