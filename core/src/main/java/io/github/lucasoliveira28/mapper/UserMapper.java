package io.github.lucasoliveira28.mapper;

import io.github.lucasoliveira28.dto.user.UserDTO;
import io.github.lucasoliveira28.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "transactionsSend", ignore = true)
    @Mapping(target = "transactionsReceived", ignore = true)
    UserDTO toUserDTO(User user);

}
