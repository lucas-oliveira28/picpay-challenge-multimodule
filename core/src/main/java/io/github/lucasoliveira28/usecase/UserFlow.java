package io.github.lucasoliveira28.usecase;

import io.github.lucasoliveira28.dto.user.UserDTO;
import io.github.lucasoliveira28.entity.User;
import io.github.lucasoliveira28.exceptions.UserBadRequestException;
import io.github.lucasoliveira28.exceptions.UserNotFoundException;
import io.github.lucasoliveira28.mapper.UserMapper;
import io.github.lucasoliveira28.port.command.UserCommand;
import io.github.lucasoliveira28.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserFlow implements UserCommand {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByCpf(userDTO.getCpf()) || userRepository.existsByEmail(userDTO.getEmail())) {
            throw new UserBadRequestException("CPF or Email already exists");
        }
        var user = userRepository.save(buildUser(userDTO));
        return userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO deleteUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(userId);
            return userMapper.toUserDTO(optionalUser.get());
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return userMapper.toUserDTO(optionalUser.get());
        } else {
            throw new UserNotFoundException("Usuário não encontrado");
        }
    }

    private User buildUser(UserDTO userDTO) {
        return User.builder()
                .fullName(userDTO.getFullName())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .userType(userDTO.getUserType())
                .balance(userDTO.getBalance())
                .build();
    }
}
