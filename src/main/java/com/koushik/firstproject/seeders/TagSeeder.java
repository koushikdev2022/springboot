package com.koushik.firstproject.seeders;


import com.koushik.firstproject.model.Tag;
import com.koushik.firstproject.repositary.PublicStatusRepository;
import com.koushik.firstproject.repositary.TagRepository;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TagSeeder implements CommandLineRunner{
    private final TagRepository tagRepository;

    public TagSeeder(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public void run(String... args) {
        if (tagRepository.count() == 0) {
            List<Tag> tags = List.of(
                Tag.builder()
                        .name("huskey")
                        .shortName("hus")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Tag.builder()
                        .name("labrador")
                        .shortName("lab")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),
                Tag.builder()
                        .name("golden retriver")
                        .shortName("gr")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                  Tag.builder()
                        .name("pug")
                        .shortName("p")
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
            );

            tagRepository.saveAll(tags);
            System.out.println("Tag seed data inserted.");
        } else {
            System.out.println("Tag table already contains data. Skipping seeding.");
        }
    }
}
