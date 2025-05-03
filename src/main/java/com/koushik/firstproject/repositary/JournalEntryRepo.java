package com.koushik.firstproject.repositary;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.koushik.firstproject.entity.JournalEntry;
import org.bson.types.ObjectId;
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId>{

}
