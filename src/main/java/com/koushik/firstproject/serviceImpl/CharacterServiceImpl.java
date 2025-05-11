package com.koushik.firstproject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.koushik.firstproject.model.Character;

import com.koushik.firstproject.dto.CharacterDTO;
import com.koushik.firstproject.repositary.CharacterRepo;
import com.koushik.firstproject.services.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {
        @Autowired
        private CharacterRepo characterRepo;
        @Override
        public Character addCharacter(CharacterDTO dto) {
            Character character = new Character();
            character.setCharacterName(dto.getCharacterName());
            character.setGender(dto.getGender());
            character.setBackgroundStory(dto.getBackgroundStory());
            character.setUserId(dto.getUserId());
            return characterRepo.save(character);
        }
}
