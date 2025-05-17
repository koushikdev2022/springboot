package com.koushik.firstproject.seeders;

import com.koushik.firstproject.model.PublicStatus;
import com.koushik.firstproject.repositary.PublicStatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PublicStatusSeeder implements CommandLineRunner {

    private final PublicStatusRepository publicStatusRepository;

    public PublicStatusSeeder(PublicStatusRepository publicStatusRepository) {
        this.publicStatusRepository = publicStatusRepository;
    }

    @Override
    public void run(String... args) {
        if (publicStatusRepository.count() == 0) {
            List<PublicStatus> statuses = List.of(
                PublicStatus.builder()
                        .name("Public")
                        .shortName("PUB")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                PublicStatus.builder()
                        .name("Private")
                        .shortName("PRI")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
            );

            publicStatusRepository.saveAll(statuses);
            System.out.println("PublicStatus seed data inserted.");
        } else {
            System.out.println("PublicStatus table already contains data. Skipping seeding.");
        }
    }
}
