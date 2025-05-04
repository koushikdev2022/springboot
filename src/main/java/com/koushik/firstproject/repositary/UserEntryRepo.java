package com.koushik.firstproject.repositary;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.koushik.firstproject.entity.JournalEntry;

import com.koushik.firstproject.entity.UserEntry;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;


public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId>{
    UserEntry findByUserName(String username);
}
