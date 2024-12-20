package com.example.doc_service.api.dto.response;


import java.util.UUID;

public record DocumentResponse(
        UUID id,
        String title,
        String content,
        String ownerId,
        boolean isPublic
) {
}

