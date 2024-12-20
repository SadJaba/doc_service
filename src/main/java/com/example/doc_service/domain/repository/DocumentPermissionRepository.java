package com.example.doc_service.domain.repository;

import com.example.doc_service.domain.model.DocumentPermission;
import com.example.doc_service.domain.model.permission.PermissionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DocumentPermissionRepository extends JpaRepository<DocumentPermission, UUID> {
    Optional<DocumentPermission> findByDocumentIdAndUserIdAndPermissionType(UUID document_id, String email, PermissionType permissionType);
}

