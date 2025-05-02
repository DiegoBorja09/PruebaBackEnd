package com.test.diego.config;

import com.test.diego.entity.Role;
import com.test.diego.entity.UserEntity;
import com.test.diego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepo.existsByUsername("admin")) {
            UserEntity admin = UserEntity.builder()
                .username("admin")
                .password(encoder.encode("Admin123"))
                .build();
            admin.getRoles().add(Role.ROLE_USER);
            admin.getRoles().add(Role.ROLE_ADMIN);
            userRepo.save(admin);
            UserEntity user = UserEntity.builder()
                    .username("admin")
                    .password(encoder.encode("Secreto123"))
                    .build();
                admin.getRoles().add(Role.ROLE_USER);
                userRepo.save(user);
        }
    }
}

