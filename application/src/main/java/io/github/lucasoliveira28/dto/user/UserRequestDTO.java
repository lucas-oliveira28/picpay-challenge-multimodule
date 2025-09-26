package io.github.lucasoliveira28.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Nome completo deve ser preenchido")
    @Length(min = 10)
    @JsonProperty("fullName")
    String fullName;

    @CPF(message = "CPF inválido")
    @JsonProperty("cpf")
    String cpf;

    @Email(message = "Email inválido")
    @JsonProperty("email")
    String email;

    @NotBlank(message = "Senha deve ser preenchida")
    @Length(min = 6)
    @JsonProperty("password")
    String password;

    @NotBlank(message = "Tipo da conta deve ser preenchido")
    @JsonProperty("userType")
    String userType;

}
