package com.koushik.firstproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.koushik.firstproject.api.controller.user.UserEntryController;
import com.koushik.firstproject.entity.JournalEntry;
import com.koushik.firstproject.entity.UserEntry;
import com.koushik.firstproject.repositary.UserEntryRepo;

@Component
public class UserEntryService {
    @Autowired
    private UserEntryRepo userEntryRepo;

    public UserEntry saveUserEntry(UserEntry UserEntry){
        return userEntryRepo.save(UserEntry);
    }
    public List<UserEntry> userList(){
        return userEntryRepo.findAll();
    }
}
