package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.dto.transaction.TransactionDTO;
import io.github.lucasoliveira28.dto.user.UserDTO;
import io.github.lucasoliveira28.entity.Transaction;
import io.github.lucasoliveira28.entity.User;
import io.github.lucasoliveira28.exceptions.TransactionBadRequestException;
import io.github.lucasoliveira28.port.command.TransactionCommand;
import io.github.lucasoliveira28.repository.TransactionRepository;
import io.github.lucasoliveira28.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionFlow implements TransactionCommand {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public TransactionDTO newTransaction(String payerUUID, String payeeUUID, Double amount) {
        if (amount == null) {
            throw new TransactionBadRequestException("Amount must not be null");
        } else if (amount <= 0) {
            throw new TransactionBadRequestException("Amount must be greater than 0");
        }

        Optional<User> payer = userRepository.findById(UUID.fromString(payerUUID));
        Optional<User> payee = userRepository.findById(UUID.fromString(payeeUUID));

        if (payer.isEmpty()) {
            throw new IllegalArgumentException("Payer not found");
        } else if (payee.isEmpty()) {
            throw new IllegalArgumentException("Payee not found");
        }

        if (BigDecimal.valueOf(amount).compareTo(payer.get().getBalance()) > 0) {
            throw new TransactionBadRequestException("Insufficient funds");
        }

        var transaction = buildTransaction(payer.get(), payee.get(), amount);

        return buildTransactionDTO(transactionRepository.save(transaction));
    }

    private Transaction buildTransaction(User payer, User payee, Double amount) {
        return Transaction.builder()
                .value(BigDecimal.valueOf(amount))
                .payee(payee)
                .payer(payer)
                .build();
    }

    private TransactionDTO buildTransactionDTO(Transaction transaction) {
        return TransactionDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getValue())
                .payer(UserDTO.builder()
                        .id(transaction.getPayer().getId())
                        .fullName(transaction.getPayer().getFullName())
                        .balance(transaction.getPayer().getBalance())
                        .build())
                .payee(UserDTO.builder()
                        .id(transaction.getPayee().getId())
                        .fullName(transaction.getPayee().getFullName())
                        .balance(transaction.getPayee().getBalance())
                        .build())
                .build();
    }
}
