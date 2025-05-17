package com.koushik.firstproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.koushik.firstproject.model.Character;
import com.koushik.firstproject.config.appLogger.AppLogger;
import com.koushik.firstproject.dto.CharacterDTO;
import com.koushik.firstproject.dto.CharacterImageDTO;
import com.koushik.firstproject.dto.CharacterListDTO;
import com.koushik.firstproject.entity.UserEntry;
import com.koushik.firstproject.repositary.CharacterRepo;
import com.koushik.firstproject.services.CharacterService;
import com.koushik.firstproject.utill.JwtUtill;
import org.springframework.beans.factory.annotation.Value;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

import com.koushik.firstproject.dto.CharacterSecondDto;

@Service
public class CharacterServiceImpl implements CharacterService {
        @Autowired
        private CharacterRepo characterRepo;
        @Autowired
        private JwtUtill jwtUtill;
        private HttpServletRequest request;
        @Value("${app.base-url}")
        private String baseUrl;
        @Override
        public Character addCharacter(CharacterDTO dto) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("Authentication: " + authentication);
            String username = authentication.getName();
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = jwtUtill.extractToken(request);
            
            if (token == null) {
                throw new RuntimeException("JWT token not found in request");
            }
            Map<String, Object>  details = jwtUtill.getUserData(token);
            // String userId = jwtUtill.extractUserId(token).toLong();
            Long userId = Long.parseLong(jwtUtill.extractUserId(token));

            String userName = (String) details.get("userName");
            String email = (String) details.get("email");
            Object roles = details.get("roles"); // may be null
            Object journalEntry = details.get("journalEntry"); 
            System.out.println("userName: " + userName);
            // String userId = userDetails.getId().toString(); 
            Random random = new Random();
            int randomNumber = 1000 + random.nextInt(9000);  // Generates a number between 1000 and 9999
            
            // Get the current date-time
            LocalDateTime now = LocalDateTime.now();
            
            // Format the date-time to a readable string (e.g., "yyyyMMdd_HHmmss")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String formattedDateTime = now.format(formatter);
            Character character = new Character();
            character.setCharacterName(dto.getCharacterName());
            character.setGender(dto.getGender());
            character.setBackgroundStory(dto.getBackgroundStory());
            character.setUserId(userId);
            character.setCharacterUniqeId(formattedDateTime);
            return characterRepo.save(character);
        }
        @Override
        public Character secondAddCharacter(Long id, CharacterSecondDto dto) {
            
            Character character = characterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
                

            character.setExampleMessage(dto.getExampleMessage());
            character.setKeyMemory(dto.getKeyMemory());
            character.setType(dto.getType());
            character.setResponseDerective(dto.getResponseDerective());
        
            return characterRepo.save(character);
        }
        @Override
        public Character imageAddCharacter(Long id, CharacterImageDTO imgdto) {
            
            Character character = characterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
                

            character.setAvatar(imgdto.getAvatar());
           
            return characterRepo.save(character);
        }
        @Override
        public CharacterListDTO listCharacter(Long id) {
            Character character = characterRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Character not found with id: " + id));

            // Build DTO
            CharacterListDTO dto = CharacterListDTO.builder()
                .id(character.getId())
                .responseDerective(character.getResponseDerective())
                .keyMemory(character.getKeyMemory())
                .exampleMessage(character.getExampleMessage())
                .videoUrl(character.getVideoUrl())
                .userId(character.getUserId())
                .characterUniqeId(character.getCharacterUniqeId())
                .characterName(character.getCharacterName())
                .dob(character.getDob())
                .gender(character.getGender())
                .avatar(baseUrl + character.getAvatar()) // prefix with base URL
                .backgroundStory(character.getBackgroundStory())
                .characterGreeting(character.getCharacterGreeting())
                .parentId(character.getParentId())
                .isActive(character.getIsActive())
                .isPublic(character.getIsPublic())
                .isPublish(character.getIsPublish())
                .type(character.getType())
                .isCompleted(character.getIsCompleted())
                .isDeleted(character.getIsDeleted())
                .createdAt(character.getCreatedAt())
                .updatedAt(character.getUpdatedAt())
                .build();

            return dto; // ✅ Correct return
        }

}
