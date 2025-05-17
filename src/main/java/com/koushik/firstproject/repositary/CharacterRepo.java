package com.koushik.firstproject.repositary;

import java.util.List;

import com.koushik.firstproject.model.Character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {
    // You can define custom queries here if needed, for example:
    // List<Character> findByUserId(Long userId);
    List<Character> findByIsActiveAndIsPublishAndIsCompletedAndIsDeleted(Integer isActive, Integer isPublish, Integer isCompleted,Integer isDeleted);

}
