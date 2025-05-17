package com.koushik.firstproject.repositary;

import java.util.Optional;

import com.koushik.firstproject.model.PublicStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PublicStatusRepository extends JpaRepository<PublicStatus, Long> {
    Optional<PublicStatus> findTopByOrderByIdAsc();
}