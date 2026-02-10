package org.registration.service;

import org.registration.exception.EmailAlreadyExistsException;
import org.registration.model.Phone;
import org.registration.model.User;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BusinessService {

    private final UserService userService;
    private final JwtService jwtService;

    public BusinessService(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    public CommonResponse registerUser(RegisterRequest registerRequest) {
        if (userService.findByEmail(registerRequest.email())) {
            throw new EmailAlreadyExistsException("El correo ya registrado: " + registerRequest.email());
        }

        User user = User.builder()
                .name(registerRequest.name())
                .email(registerRequest.email())
                .password(registerRequest.password())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .token(jwtService.generateToken(registerRequest.email()))
                .phones(registerRequest.phones().stream()
                        .map(phoneRequest -> Phone.builder()
                                .number(phoneRequest.number())
                                .citycode(phoneRequest.citycode())
                                .countrycode(phoneRequest.countrycode())
                                .build())
                        .toList())
                .build();

        userService.saveUser(user);

        return new CommonResponse(user.getId(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.getIsActive());
    }
}
