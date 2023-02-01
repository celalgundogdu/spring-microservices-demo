package com.celalgundogdu.organizationservice.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime timestamp,
        String message,
        String path,
        List<String> details) {
}
