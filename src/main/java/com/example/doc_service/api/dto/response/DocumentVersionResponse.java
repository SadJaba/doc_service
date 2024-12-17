package com.example.doc_service.api.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record DocumentVersionResponse (
        UUID id,
        String content,
        LocalDateTime timestamp
){
}
