package io.github.lucasoliveira28.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionRequestDTO {

    @JsonProperty("value")
    Double amount;

    @JsonProperty("payer")
    String payerUUID;

    @JsonProperty("payee")
    String payeeUUID;

}
