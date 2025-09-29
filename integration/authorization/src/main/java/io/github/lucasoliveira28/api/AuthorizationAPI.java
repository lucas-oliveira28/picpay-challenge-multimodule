package io.github.lucasoliveira28.api;

import io.github.lucasoliveira28.dto.AuthorizationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorization", url = "https://util.devi.tools/api/v2")
public interface AuthorizationAPI {

    @GetMapping("/authorize")
    AuthorizationDTO authorize();

}
