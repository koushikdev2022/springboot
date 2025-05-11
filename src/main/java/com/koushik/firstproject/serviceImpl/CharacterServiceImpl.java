package com.koushik.firstproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.koushik.firstproject.model.Character;
import com.koushik.firstproject.config.appLogger.AppLogger;
import com.koushik.firstproject.dto.CharacterDTO;
import com.koushik.firstproject.entity.UserEntry;
import com.koushik.firstproject.repositary.CharacterRepo;
import com.koushik.firstproject.services.CharacterService;
import com.koushik.firstproject.utill.JwtUtill;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class CharacterServiceImpl implements CharacterService {
        @Autowired
        private CharacterRepo characterRepo;
        @Autowired
        private JwtUtill jwtUtill;
        private HttpServletRequest request;
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
            // String userId = jwtUtill.extractUserId(token).toLong();
            Long userId = Long.parseLong(jwtUtill.extractUserId(token));


            System.out.println("userDetails: " + userId);
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
}
