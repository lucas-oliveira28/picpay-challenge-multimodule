package io.github.lucasoliveira28.dto.authorization;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Authorization {

    private String status;
    private DataDTO data;

    @Data
    public static class DataDTO {
        private Boolean authorization;
    }

}
