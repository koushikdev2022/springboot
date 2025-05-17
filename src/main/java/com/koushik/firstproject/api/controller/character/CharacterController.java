package com.koushik.firstproject.api.controller.character;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.koushik.firstproject.dto.CharacterImageDTO;
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

                
                // Define upload directory
                String userUploadDirPath = projectRoot + "/public/upload/" + id + "/";
                File userUploadDir = new File(userUploadDirPath);
                if (!userUploadDir.exists()) {
                    userUploadDir.mkdirs();
                }

                // Get original file name and extension
                String originalFilename = file.getOriginalFilename();
                String fileExtension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf(".")); // Remove extension
                }

                // Create a timestamp
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

                // Final file name: originalName_yyyyMMdd_HHmmss.extension
                String newFileName = originalFilename + "_" + timestamp + fileExtension;
                CharacterImageDTO characterDto = new CharacterImageDTO();
                // Save the file
                String filePath = userUploadDirPath + newFileName;
                file.transferTo(new File(filePath));
                characterDto.setAvatar("/public/upload/" + id + "/" + newFileName); // Relative path or full URL
                
                Character seconadAddCharacter = characterServiceImpl.imageAddCharacter(id,characterDto);
                return ResponseEntity.status(201).body(Map.of(
                    "status", true,
                    "message", "character insert successfully",
                    "status_code", 201,
                    "seconadAddCharacter",seconadAddCharacter
                  ));
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
            }
      
       }
}
