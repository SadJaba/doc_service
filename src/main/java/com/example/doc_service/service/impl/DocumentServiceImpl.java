package com.example.doc_service.service.impl;

import com.example.doc_service.api.dto.request.CreateDocumentRequest;
import com.example.doc_service.api.dto.request.UpdateDocumentRequest;
import com.example.doc_service.api.dto.response.DocumentResponse;
import com.example.doc_service.api.dto.response.DocumentVersionResponse;
import com.example.doc_service.domain.model.Document;
import com.example.doc_service.domain.model.DocumentVersion;
import com.example.doc_service.domain.model.permission.PermissionType;
import com.example.doc_service.domain.repository.DocumentPermissionRepository;
import com.example.doc_service.domain.repository.DocumentRepository;
import com.example.doc_service.domain.repository.DocumentVersionRepository;
import com.example.doc_service.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository versionRepository;
    private final DocumentPermissionRepository permissionRepository;

    @Override
    public DocumentResponse getDocument(UUID documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));
//        if (!document.isPublic() && !document.getOwnerId().equals(userId)) {
//            permissionRepository.findByDocumentIdAndUserIdAndPermissionType(documentId, userId, PermissionType.VIEW)
//                    .orElseThrow(() -> new RuntimeException("You don't have permission to view this document"));
//        }

        return document.toDto();
    }

    @Override
    public DocumentResponse createDocument(CreateDocumentRequest request) {
        Document document = Document.builder()
                .title(request.title())
                .content(request.content())
                .ownerId(request.ownerId())
                .isPublic(request.isPublic())
                .build();
        document = documentRepository.save(document);
        saveVersion(document, request.content());
        return documentRepository.save(document).toDto();
    }

    @Override
    public DocumentResponse updateDocument(UUID documentId, UpdateDocumentRequest request) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
        document.setContent(request.content());
        documentRepository.save(document);
        saveVersion(document, request.content());
        return null;
    }

    @Override
    public List<DocumentResponse> getAllDocuments(){
        return documentRepository.findAll().stream().map(Document::toDto).toList();
    }

    @Override
    public DocumentResponse deleteDocument(UUID documentId) {
        Document document = findDocument(documentId);
        documentRepository.delete(document);
        return document.toDto();
    }

    @Override
    public List<DocumentVersionResponse> getDocumentHistory(UUID documentId) {
        return versionRepository.findAllByDocumentId(documentId).stream().map(DocumentVersion::toDto).toList();
    }

    private void saveVersion(Document document, String content) {
        DocumentVersion version = DocumentVersion.builder()
                .document(document)
                .content(content)
                .timestamp(LocalDateTime.now())
                .build();
        versionRepository.save(version);
    }

    private Document findDocument(UUID documentId) {
        Optional<Document> document = documentRepository.findById(documentId);
        return document.orElseThrow(() -> new IllegalArgumentException("Document not found"));
    }
}
