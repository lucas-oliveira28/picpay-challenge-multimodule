package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.user.UserRequestDTO;
import io.github.lucasoliveira28.dto.user.UserResponseDTO;
import io.github.lucasoliveira28.dto.user.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    UserDTO toUserDTO(UserRequestDTO userRequestDTO);

    @Mapping(target = "password", ignore = true)
    UserResponseDTO toUserResponseDTO(UserDTO userDTO);

}
