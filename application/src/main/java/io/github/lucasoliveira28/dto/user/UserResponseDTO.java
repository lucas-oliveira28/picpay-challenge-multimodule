package io.github.lucasoliveira28.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponseDTO {

    @JsonProperty("id")
    UUID id;

    @JsonProperty("fullName")
    String fullName;

    @JsonProperty("cpf")
    String cpf;

    @JsonProperty("email")
    String email;

    @JsonProperty("userType")
    String userType;

    @JsonProperty("balance")
    BigDecimal balance;

}
