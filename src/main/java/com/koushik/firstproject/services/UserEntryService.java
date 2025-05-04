package com.koushik.firstproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koushik.firstproject.entity.UserEntry;
import com.koushik.firstproject.repositary.UserEntryRepo;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserEntryService {
    @Autowired
    private UserEntryRepo userEntryRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserEntry saveUserEntry(UserEntry userEntry) {
        // Hash the password before saving
        String hashedPassword = passwordEncoder.encode(userEntry.getPassword());
        userEntry.setPassword(hashedPassword);  
        return userEntryRepo.save(userEntry);
    }

    public List<UserEntry> userList() {
        return userEntryRepo.findAll();
    }

    public UserEntry userListSingle(String username) {
        return userEntryRepo.findByUserName(username);
    }
}
