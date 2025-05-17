package com.koushik.firstproject.repositary;

import com.koushik.firstproject.model.PublicStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicStatusRepository extends JpaRepository<PublicStatus, Long> {
}