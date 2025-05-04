package com.koushik.firstproject.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import jakarta.persistence.Id;
import lombok.Data;

@Document(collection = "users")
@Data
public class UserEntry {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @Indexed(unique = true)
    @NonNull
    private String email;
    private List<String> roles;
    @DBRef
    private List<JournalEntry> journalEntry = new ArrayList<>();
}
