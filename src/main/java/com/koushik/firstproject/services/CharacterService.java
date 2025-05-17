package com.koushik.firstproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.firstproject.dto.CharacterDTO;
import com.koushik.firstproject.dto.CharacterSecondDto;
import com.koushik.firstproject.dto.CharacterImageDTO;
import com.koushik.firstproject.repositary.CharacterRepo;
import com.koushik.firstproject.model.Character ;

public interface CharacterService {
    Character addCharacter(CharacterDTO characterDTO);
    Character secondAddCharacter(Long id,CharacterSecondDto characterSecondDTO);
    Character imageAddCharacter(Long id,CharacterImageDTO characterImageDTO);
}

