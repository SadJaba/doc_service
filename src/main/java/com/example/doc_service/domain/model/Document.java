package com.example.doc_service.domain.model;

import com.example.doc_service.api.dto.response.DocumentResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentVersion> versions = new ArrayList<>();

    @Column(nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private boolean isPublic;

    public DocumentResponse toDto() {
        return new DocumentResponse(
                id,
                title,
                content,
                ownerId,
                isPublic
        );
    }
}
