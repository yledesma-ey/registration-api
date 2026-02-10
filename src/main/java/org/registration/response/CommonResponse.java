package org.registration.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommonResponse(UUID id,
                             LocalDateTime created,
                             LocalDateTime modified,
                             LocalDateTime lastLogin,
                             String token,
                             Boolean isActive) {}
