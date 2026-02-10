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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Boolean findByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }


}
