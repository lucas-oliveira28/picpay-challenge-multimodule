package io.github.lucasoliveira28.repository;

import io.github.lucasoliveira28.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);

}
