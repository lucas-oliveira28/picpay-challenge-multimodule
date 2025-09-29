package io.github.lucasoliveira28.port.command;

import io.github.lucasoliveira28.dto.user.UserDTO;

import java.util.UUID;

public interface UserCommand {

    UserDTO createUser(UserDTO userDTO);
    UserDTO deleteUserById(UUID userId);
    UserDTO getUserById(UUID userId);

}
