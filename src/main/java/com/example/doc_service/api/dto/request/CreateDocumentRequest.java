package com.example.doc_service.api.dto.request;


public record CreateDocumentRequest(
        String title,
        String content,
        String ownerId,
        boolean isPublic
){
}
