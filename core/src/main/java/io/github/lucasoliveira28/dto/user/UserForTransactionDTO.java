package io.github.lucasoliveira28.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserForTransactionDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("fullName")
    private String fullName;

}
