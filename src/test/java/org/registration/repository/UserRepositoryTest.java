package org.registration.repository;

import org.junit.jupiter.api.Test;
import org.registration.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        User user = User.builder()
                .name("testuser")
                .email("testuser@email.com")
                .password("USER")
                .isActive(Boolean.TRUE)
                .build();
        userRepository.save(user);

        Optional<User> userFound = userRepository.findByEmail("testuser@email.com");
        assertTrue(userFound.isPresent());
        assertEquals("testuser", userFound.get().getName());
    }
}
