package com.koushik.firstproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicStatusDTO {
    private Long id;
    private String name;
    private String shortName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
