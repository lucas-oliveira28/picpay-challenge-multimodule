package io.github.lucasoliveira28.exceptions;

public class TransactionBadRequestException extends RuntimeException {
    public TransactionBadRequestException(String message) {
        super(message);
    }
}
