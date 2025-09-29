package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.user.UserDTO;
import io.github.lucasoliveira28.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

}
