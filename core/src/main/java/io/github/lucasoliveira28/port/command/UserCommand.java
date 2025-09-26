package io.github.lucasoliveira28.port.command;

import io.github.lucasoliveira28.dto.user.UserDTO;

public interface UserCommand {

    UserDTO createUser(UserDTO userDTO);
    UserDTO deleteUserById(Long userId);
    UserDTO getUserById(Long userId);

}
