package org.registration.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.registration.exception.EmailAlreadyExistsException;
import org.registration.model.User;
import org.registration.repository.UserRepository;
import org.registration.request.PhoneRequest;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BusinessService businessService;


    @Test
    void testRegisterUserSuccessfully() {
        RegisterRequest registerRequest = new RegisterRequest(
                "testuser",
                "test@user.com",
                "password123",
                List.of(new PhoneRequest("1234567890", "01", "001"))
        );

        when(userService.findByEmail(registerRequest.email())).thenReturn(false);
        when(jwtService.generateToken(registerRequest.email())).thenReturn("mock-token");

        CommonResponse response = businessService.registerUser(registerRequest);

        assertNotNull(response);
        assertEquals("mock-token", response.token());
        assertTrue(response.isActive());
    }

    @Test
    void testRegisterUserEmailExists() {
        RegisterRequest registerRequest = new RegisterRequest(
                "testuser",
                "test@user.com",
                "password123",
                List.of(new PhoneRequest("1234567890", "01", "001"))
        );
        when(userService.findByEmail(registerRequest.email())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class,
                () -> businessService.registerUser(registerRequest));

        verify(userRepository, never()).save(any());
    }
}
