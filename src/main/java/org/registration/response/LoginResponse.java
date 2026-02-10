package org.registration.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record LoginResponse(UUID id,
                            String token,
                            LocalDateTime lastLogin,
                            Boolean isActive) {}
