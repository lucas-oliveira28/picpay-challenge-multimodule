package io.github.lucasoliveira28.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRequestDTO {

    @NotBlank(message = "fullName deve ser preenchido")
    @Length(min = 10)
    @JsonProperty("fullName")
    private String fullName;

    @CPF(message = "cpf inválido")
    @JsonProperty("cpf")
    private String cpf;

    @Email(message = "email inválido")
    @JsonProperty("email")
    private String email;

    @NotBlank(message = "password deve ser preenchida")
    @Length(min = 6)
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "userType deve ser preenchido")
    @JsonProperty("userType")
    private String userType;

    @NotNull(message = "balance não pode ser nulo")
    @JsonProperty("balance")
    private Double balance;

}
