package com.example.doc_service.api.dto.request;


import java.util.UUID;

public record CreateDocumentRequest(
        String title,
        String content,
        String ownerId,
        boolean isPublic
){
}
