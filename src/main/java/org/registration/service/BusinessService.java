package org.registration.service;

import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    private final UserService userService;

    public BusinessService(UserService userService) {
        this.userService = userService;
    }

    public CommonResponse registerUser(RegisterRequest registerRequest) {
        return userService.register(registerRequest);
    }
}
