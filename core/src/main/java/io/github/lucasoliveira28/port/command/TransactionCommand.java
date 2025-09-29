package io.github.lucasoliveira28.port.command;

import io.github.lucasoliveira28.dto.transaction.TransactionDTO;

public interface TransactionCommand {
    TransactionDTO newTransaction(String payerUUID, String payeeUUID, Double amount);
    TransactionDTO getTransactionById(String transactionId);
}
