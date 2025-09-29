package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.NotificationDTO;
import io.github.lucasoliveira28.dto.notification.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationErrorMapper {

    Notification toNotificationError(NotificationDTO notificationErrorDTO);

}
