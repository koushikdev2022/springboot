package com.koushik.firstproject.api.controller.character;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import  com.koushik.firstproject.model.Character;

import com.koushik.firstproject.dto.CharacterDTO;
import com.koushik.firstproject.dto.CharacterSecondDto;
import com.koushik.firstproject.serviceImpl.CharacterServiceImpl;
import com.koushik.firstproject.services.CharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
       @Autowired
       private CharacterServiceImpl characterServiceImpl;
       @PostMapping("/add")
       public ResponseEntity<?> addCharacter(@Valid @RequestBody CharacterDTO characterDTO) {
            Character savedCharacter = characterServiceImpl.addCharacter(characterDTO);
            // return ResponseEntity.ok(savedCharacter);
            return ResponseEntity.status(201).body(Map.of(
                "status", true,
                "message", "character insert successfully",
                "status_code", 201,
                "data", savedCharacter
            ));
       }
       @PutMapping("/second-add/{id}")
       public ResponseEntity<?> secondAdd(@Valid @PathVariable Long id,@RequestBody CharacterSecondDto characterSecondDTO){
        System.out.println("DTO: controller " + characterSecondDTO);
        Character seconadAddCharacter = characterServiceImpl.secondAddCharacter(id,characterSecondDTO);
        
        return ResponseEntity.status(201).body(Map.of(
            "status", true,
            "message", "character insert successfully",
            "status_code", 201,
            "seconadAddCharacter",seconadAddCharacter
          ));
       }
}
