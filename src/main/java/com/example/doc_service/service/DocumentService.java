package com.example.doc_service.service;

import com.example.doc_service.api.dto.request.CreateDocumentRequest;
import com.example.doc_service.api.dto.request.UpdateDocumentRequest;
import com.example.doc_service.api.dto.response.DocumentResponse;
import com.example.doc_service.api.dto.response.DocumentVersionResponse;
import com.example.doc_service.domain.model.DocumentVersion;

import java.util.List;
import java.util.UUID;

public interface DocumentService {
    DocumentResponse getDocument(UUID documentId);
    DocumentResponse createDocument(CreateDocumentRequest request);
    DocumentResponse updateDocument(UUID documentId, UpdateDocumentRequest request);
    List<DocumentResponse> getAllDocuments();
    DocumentResponse deleteDocument(UUID documentId);
    List<DocumentVersionResponse> getDocumentHistory(UUID documentId);
}
