package com.koushik.firstproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import org.bson.types.ObjectId;

@Entity
@Table(name = "characters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String responseDerective;

    @Column(columnDefinition = "TEXT")
    private String keyMemory;

    @Column(columnDefinition = "TEXT")
    private String exampleMessage;

    private String videoUrl;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String characterUniqeId;

    @Column(name = "character_name", nullable = false)
    private String characterName;

    private Date dob;

    @Column(nullable = false)
    private String gender;

    private String avatar;

    @Column(columnDefinition = "TEXT")
    private String backgroundStory;

    @Column(columnDefinition = "TEXT")
    private String characterGreeting;

    @Column(name = "parent_id", nullable = false)
    private Integer parentId = 0;

    @Column(nullable = false)
    private Integer isActive = 1;

    @Column(name = "public", nullable = false)
    private Integer isPublic = 0;

    @Column(nullable = false)
    private Integer isPublish = 0;

    @Column(columnDefinition = "INT DEFAULT 1 COMMENT '1 anime 2 photoreal'")
    private Integer type = 1;

    private Integer isCompleted = 0;

    private Integer isDeleted = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // Relationships

   
}
