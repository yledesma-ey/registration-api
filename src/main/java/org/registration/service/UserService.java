package org.registration.service;

import org.registration.exception.EmailAlreadyExistsException;
import org.registration.model.Phone;
import org.registration.model.User;
import org.registration.repository.UserRepository;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public CommonResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.email()).isPresent()) {
            throw new EmailAlreadyExistsException();
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

        userRepository.save(user);

        return new CommonResponse(user.getId(),
                user.getCreated(),
                user.getModified(),
                user.getLastLogin(),
                user.getToken(),
                user.getIsActive());
    }
}
