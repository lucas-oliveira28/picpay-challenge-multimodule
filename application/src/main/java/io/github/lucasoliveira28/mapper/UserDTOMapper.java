package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.user.UserDTO;
import io.github.lucasoliveira28.dto.user.UserRequestDTO;
import io.github.lucasoliveira28.dto.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    @Mapping(target = "id", ignore = true)
    UserDTO toUserDTO(UserRequestDTO userRequestDTO);

    UserResponseDTO toUserResponseDTO(UserDTO userDTO);

}
