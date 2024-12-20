package com.example.doc_service.api.controller;

import com.example.doc_service.api.dto.request.CreateDocumentRequest;
import com.example.doc_service.api.dto.request.UpdateDocumentRequest;
import com.example.doc_service.api.dto.response.DocumentResponse;
import com.example.doc_service.api.dto.response.DocumentVersionResponse;
import com.example.doc_service.service.DocumentService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/{id}")
    public ResponseEntity<DocumentResponse> getDocument(@PathVariable UUID id) {
        return ResponseEntity.ok().body(documentService.getDocument(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        return ResponseEntity.ok().body(documentService.getAllDocuments());
    }

    @PostMapping("/new")
    public ResponseEntity<DocumentResponse> createDocument(
            @RequestBody CreateDocumentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(documentService.createDocument(request));
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<DocumentResponse> updateDocument(
            @PathVariable UUID id,
            @RequestBody UpdateDocumentRequest request) {
        return ResponseEntity.ok().body(documentService.updateDocument(id, request));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DocumentResponse> deleteDocument(@PathVariable UUID id) {
        return ResponseEntity.ok().body(documentService.deleteDocument(id));
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<List<DocumentVersionResponse>> getDocumentHistory(@PathVariable UUID id) {
        return ResponseEntity.ok().body(documentService.getDocumentHistory(id));
    }



}
