package io.github.lucasoliveira28.controller;

import io.github.lucasoliveira28.dto.user.UserRequestDTO;
import io.github.lucasoliveira28.dto.user.UserResponseDTO;
import io.github.lucasoliveira28.mapper.UserDTOMapper;
import io.github.lucasoliveira28.port.command.UserCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserDTOMapper userDTOMapper;
    private final UserCommand userCommand;

    @PostMapping("/user/new")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        var userDTO = userDTOMapper.toUserDTO(userRequestDTO);
        var userCreated = userCommand.createUser(userDTO);
        var userResponseDTO = userDTOMapper.toUserResponseDTO(userCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable("id") Long userId) {
        var userDTO = userCommand.getUserById(userId);
        var userResponseDTO = userDTOMapper.toUserResponseDTO(userDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> deleteUserById(@PathVariable("id") Long userId) {
        var userDTO = userCommand.deleteUserById(userId);
        var userResponseDTO = userDTOMapper.toUserResponseDTO(userDTO);
        return ResponseEntity.ok(userResponseDTO);
    }
}
