package com.koushik.firstproject.repositary;


import java.util.Optional;

import com.koushik.firstproject.model.PublicStatus;
import com.koushik.firstproject.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface TagRepository extends JpaRepository<Tag, Long>{
    
} 