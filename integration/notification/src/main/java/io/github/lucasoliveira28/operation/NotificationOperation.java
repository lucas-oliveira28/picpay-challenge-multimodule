package io.github.lucasoliveira28.operation;

import io.github.lucasoliveira28.api.NotificationAPI;
import io.github.lucasoliveira28.dto.NotificationDTO;
import io.github.lucasoliveira28.dto.notification.Notification;
import io.github.lucasoliveira28.mapper.NotificationErrorMapper;
import io.github.lucasoliveira28.port.integration.NotificationIntegration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationOperation implements NotificationIntegration {

    private final NotificationAPI api;
    private final NotificationErrorMapper mapper;

    @Override
    public Notification sendNotification() {
        try {
            ResponseEntity<Void> resp = api.notification();
            if (resp.getStatusCode().is2xxSuccessful()) {
                return mapper.toNotificationError(NotificationDTO.builder()
                        .status("success")
                        .message("Notificação enviada com sucesso!")
                        .build());
            }
        } catch (Exception e) {
            return mapper.toNotificationError(NotificationDTO.builder()
                    .status("error")
                    .message(e.getMessage())
                    .build());
        }
        return mapper.toNotificationError(NotificationDTO.builder()
                .status("error")
                .message("Erro no envio da notificação, tente novamente mais tarde.")
                .build());
    }

}


