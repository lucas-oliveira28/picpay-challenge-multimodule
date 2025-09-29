package io.github.lucasoliveira28.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDTO {

    @NotNull(message = "value não pode ser nulo")
    @JsonProperty("value")
    private Double amount;

    @NotBlank(message = "payerId deve ser preenchido")
    @JsonProperty("payerId")
    private String payerUUID;

    @NotBlank(message = "payeeId deve ser preenchido")
    @JsonProperty("payeeId")
    private String payeeUUID;

}
