package org.registration.controller;

import jakarta.validation.Valid;
import org.registration.request.RegisterRequest;
import org.registration.response.CommonResponse;
import org.registration.service.BusinessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final BusinessService businessService;

    public UserController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CommonResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        CommonResponse response = businessService.registerUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
