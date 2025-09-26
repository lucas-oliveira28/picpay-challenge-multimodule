package io.github.lucasoliveira28.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lucasoliveira28.dto.user.UserDTO;
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
public class TransactionDTO {

    @JsonProperty("id")
    UUID id;

    @JsonProperty("value")
    BigDecimal amount;

    @JsonProperty("payer")
    UserDTO payer;

    @JsonProperty("payee")
    UserDTO payee;

}
