package com.koushik.firstproject.api.controller.character;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import  com.koushik.firstproject.model.Character;

import com.koushik.firstproject.dto.CharacterDTO;
import com.koushik.firstproject.dto.CharacterSecondDto;
import com.koushik.firstproject.serviceImpl.CharacterServiceImpl;
import com.koushik.firstproject.services.CharacterService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/characters")
public class CharacterController {
       private static final String BASE_UPLOAD_DIR = "public/upload/";
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
       @PutMapping("/image-add/{id}")
       public ResponseEntity<?> imageUpload(@Valid @PathVariable("id") Long id,
            @RequestParam("file") MultipartFile file){

           

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please upload a valid file.");
            }
            try {
                // Create user-specific upload directory
                String projectRoot = new File("").getAbsolutePath(); // Points to your project root

                // Define target directory: [project_root]/public/upload/{user_id}/
                String userUploadDirPath = projectRoot + "/public/upload/" + id + "/";
                File userUploadDir = new File(userUploadDirPath);
                if (!userUploadDir.exists()) {
                    userUploadDir.mkdirs(); // Create folders if they don't exist
                }

                // Save the uploaded file
                String filePath = userUploadDirPath + file.getOriginalFilename();
                file.transferTo(new File(filePath));
    
                return ResponseEntity.status(201).body(Map.of(
                    "status", true,
                    "message", "character insert successfully",
                    "status_code", 201
                   
                  ));
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
            }
      
       }
}
