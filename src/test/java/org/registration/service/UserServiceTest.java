package org.registration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.registration.repository.UserRepository;
import org.registration.request.PhoneRequest;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp(){
        UserRepository userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void testRegisterUser() {
        RegisterRequest registerRequest = new RegisterRequest(
                "testuser",
                "test@user.com",
                "password123",
                List.of(new PhoneRequest("1234567890", "01", "001"))
        );

        CommonResponse response = userService.register(registerRequest);

        assertNotNull(response.id());
        assertTrue(response.isActive());
    }
}
