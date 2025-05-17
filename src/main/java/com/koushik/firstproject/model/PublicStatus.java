package com.koushik.firstproject.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import org.bson.types.ObjectId;

@Entity
@Table(name = "public_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String name;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String shortName;
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
