package org.registration.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record RegisterRequest(String name,
                              @Email(message = "Invalid email format")
                              String email,
                              @Pattern(
                                      regexp = "^[A-Z][a-z]+\\d{2}$",
                                      message = "La contraseña debe contener al menos 2 dígitos, una letra mayúscula y letras minúsculas"
                              )
                              String password,
                              List<PhoneRequest> phones) {}
