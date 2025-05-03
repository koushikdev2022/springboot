package com.koushik.firstproject.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.koushik.firstproject.entity.JournalEntry;
import com.koushik.firstproject.repositary.JournalEntryRepo;
import org.bson.types.ObjectId;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public JournalEntry saveJournalEntry(JournalEntry journalEntry){
        return journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }
    public Optional<JournalEntry> getAllbyId(ObjectId id){
        return journalEntryRepo.findById(id);
    }
    public boolean deleteIfExists(ObjectId id) {
        Optional<JournalEntry> entry = journalEntryRepo.findById(id);
        if (entry.isPresent()) {
            journalEntryRepo.deleteById(id);
            return true; // Deleted successfully
        }
        return false; // Not found
    }
    
}
