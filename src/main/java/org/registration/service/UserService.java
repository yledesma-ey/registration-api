package org.registration.service;

import org.registration.model.Phone;
import org.registration.model.User;
import org.registration.repository.UserRepository;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CommonResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name(registerRequest.name())
                .email(registerRequest.email())
                .password(registerRequest.password())
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .isActive(true)
                .phones(registerRequest.phones().stream()
                        .map(phoneRequest -> Phone.builder()
                                .number(phoneRequest.number())
                                .cityCode(phoneRequest.cityCode())
                                .countryCode(phoneRequest.countryCode())
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
