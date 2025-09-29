package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.dto.transaction.TransactionDTO;
import io.github.lucasoliveira28.dto.user.UserForTransactionDTO;
import io.github.lucasoliveira28.entity.Transaction;
import io.github.lucasoliveira28.entity.User;
import io.github.lucasoliveira28.entity.UserType;
import io.github.lucasoliveira28.exceptions.TransactionBadRequestException;
import io.github.lucasoliveira28.exceptions.TransactionNotAuthorizedException;
import io.github.lucasoliveira28.port.command.TransactionCommand;
import io.github.lucasoliveira28.port.integration.AuthorizationIntegration;
import io.github.lucasoliveira28.port.integration.NotificationIntegration;
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
    private final AuthorizationIntegration authorization;
    private final NotificationIntegration notification;

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

        if (payer.get().getUserType().equals(UserType.MERCHANT)) {
            throw new TransactionBadRequestException("MERCHANT not allowed to send transactions");
        }

        if (BigDecimal.valueOf(amount).compareTo(payer.get().getBalance()) > 0) {
            throw new TransactionBadRequestException("Insufficient funds");
        }

        var authorizationDTO = authorization.getAuthorization();

        if (!authorizationDTO.getData().getAuthorization()) {
            throw new TransactionNotAuthorizedException("Transação não autorizada");
        }

        payer.get().setBalance(payer.get().getBalance().subtract(BigDecimal.valueOf(amount)));
        payee.get().setBalance(payee.get().getBalance().add(BigDecimal.valueOf(amount)));

        userRepository.save(payer.get());
        userRepository.save(payee.get());

        var transaction = buildTransaction(payer.get(), payee.get(), amount);

        var notificationDTO = notification.sendNotification();

        if (!notificationDTO.getStatus().equals("success")) {
            System.out.println("Notificação não foi enviada, tente novamente mais tarde.");
        } else {
            System.out.println("Notificação enviada com sucesso!");
        }

        return buildTransactionDTO(transactionRepository.save(transaction));
    }

    @Override
    public TransactionDTO getTransactionById(String transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(UUID.fromString(transactionId));
        if (transaction.isEmpty()) {
            throw new IllegalArgumentException("Transaction not found");
        }
        return buildTransactionDTO(transaction.get());
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
                .payer(UserForTransactionDTO.builder()
                        .id(transaction.getPayer().getId())
                        .fullName(transaction.getPayer().getFullName())
                        .build())
                .payee(UserForTransactionDTO.builder()
                        .id(transaction.getPayee().getId())
                        .fullName(transaction.getPayee().getFullName())
                        .build())
                .build();
    }
}
