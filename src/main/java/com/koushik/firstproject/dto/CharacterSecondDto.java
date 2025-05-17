package com.koushik.firstproject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterSecondDto {
    @NotBlank(message = "exampleMessage is required")
    private String exampleMessage;

    @JsonProperty("key_memory")
    @NotBlank(message = "key_memory is required")
    private String keyMemory;

    @NotBlank(message = "type story is required")
    private Integer type;

    @NotBlank(message = "responseDerective is required")
    private String responseDerective;
}
