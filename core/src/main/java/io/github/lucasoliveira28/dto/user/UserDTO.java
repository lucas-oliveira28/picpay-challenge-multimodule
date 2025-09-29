package io.github.lucasoliveira28.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lucasoliveira28.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("userType")
    private UserType userType;

    @JsonProperty("balance")
    private Double balance;

}
