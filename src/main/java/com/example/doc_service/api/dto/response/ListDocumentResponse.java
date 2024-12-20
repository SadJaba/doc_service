package com.example.doc_service.api.dto.response;

import java.util.List;

public record ListDocumentResponse(
        List<DocumentResponse> documents
) {
}
