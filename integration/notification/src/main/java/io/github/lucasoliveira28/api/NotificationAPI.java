package io.github.lucasoliveira28.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notification", url = "https://util.devi.tools/api/v1")
public interface NotificationAPI {

    @PostMapping("/notify")
    ResponseEntity<Void> notification();

}
