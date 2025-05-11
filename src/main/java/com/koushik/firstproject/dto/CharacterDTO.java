package com.koushik.firstproject.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDTO {

    @NotBlank(message = "Character name is required")
    private String characterName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Background story is required")
    private String backgroundStory;

}