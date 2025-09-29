package io.github.lucasoliveira28.controller;

import io.github.lucasoliveira28.dto.transaction.TransactionRequestDTO;
import io.github.lucasoliveira28.dto.transaction.TransactionResponseDTO;
import io.github.lucasoliveira28.mapper.TransactionDTOMapper;
import io.github.lucasoliveira28.port.command.TransactionCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TransactionController {

    private final TransactionCommand command;
    private final TransactionDTOMapper mapper;

    @PostMapping("/transaction/new")
    public ResponseEntity<TransactionResponseDTO> newTransaction(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
        var transactionResponseDTO = command.newTransaction(
                transactionRequestDTO.getPayerUUID(),
                transactionRequestDTO.getPayeeUUID(),
                transactionRequestDTO.getAmount());
        return ResponseEntity.ok(mapper.toTransactionResponseDTO(transactionResponseDTO));
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable("id") String transactionId) {
        var transactionResponseDTO = command.getTransactionById(transactionId);
        return ResponseEntity.ok(mapper.toTransactionResponseDTO(transactionResponseDTO));
    }

}
