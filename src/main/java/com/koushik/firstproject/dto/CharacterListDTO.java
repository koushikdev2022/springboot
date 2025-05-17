package com.koushik.firstproject.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterListDTO {
    private Long id;
    private String responseDerective;
    private String keyMemory;
    private String exampleMessage;
    private String videoUrl;
    private Long userId;
    private String characterUniqeId;
    private String characterName;
    private Date dob;
    private String gender;
    private String avatar;
    private String backgroundStory;
    private String characterGreeting;
    private Integer parentId;
    private Integer isActive;
    private Integer isPublic;
    private Integer isPublish;
    private Integer type;
    private Integer isCompleted;
    private Integer isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PublicStatusDTO publicStatus;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PublicStatusDTO {
        private Long id;
        private String name;
        private String shortName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
