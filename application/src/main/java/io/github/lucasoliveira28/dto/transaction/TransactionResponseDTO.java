package io.github.lucasoliveira28.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lucasoliveira28.dto.user.UserForTransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TransactionResponseDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("value")
    private BigDecimal amount;

    @JsonProperty("payer")
    private UserForTransactionDTO payer;

    @JsonProperty("payee")
    private UserForTransactionDTO payee;

}
