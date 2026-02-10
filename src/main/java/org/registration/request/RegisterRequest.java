package org.registration.request;

import jakarta.validation.constraints.Email;

import java.util.List;

public record RegisterRequest(String name,
                              @Email(message = "Invalid email format")
                              String email,
                              String password,
                              List<PhoneRequest> phones) {}
