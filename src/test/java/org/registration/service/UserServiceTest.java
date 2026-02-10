package org.registration.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.registration.repository.UserRepository;
import org.registration.request.PhoneRequest;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest {

    private UserService userService;
    private JwtService jwtService;

    @BeforeEach
    void setUp(){
        UserRepository userRepository = mock(UserRepository.class);
        jwtService = mock(JwtService.class);
        userService = new UserService(userRepository, jwtService);
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

        assertTrue(response.isActive());
    }
}
