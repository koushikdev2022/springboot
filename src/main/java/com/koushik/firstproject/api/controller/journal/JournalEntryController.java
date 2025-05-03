package com.koushik.firstproject.api.controller.journal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.koushik.firstproject.entity.JournalEntry;
import com.koushik.firstproject.services.JournalEntryService;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping("/create-journal")
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntryService.saveJournalEntry(journalEntry);
    }

    @GetMapping("/list-journal")
    public ResponseEntity<Object>  showMap() {
        List<JournalEntry> journalEntry = journalEntryService.getAll();
        if (journalEntry.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                "status", true,
                "message", "Journal entry fetched successfully",
                "status_code", 200,
                "data", journalEntry
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                "status", false,
                "message", "Journal entry not found",
                "status_code", 404
            ));
        }
    }
    // @GetMapping("/list-journal/{myId}")
    // public Optional<Object> byId(@PathVariable ObjectId myId) {
    //     return ResponseEntity.ok(Map.of(
    //         "status", true,
    //         "message", "Journal entry deleted successfully",
    //         "status_code", 200,
    //         "data",journalEntryService.getAllbyId(myId);
    //     ));
       
    // }
    @GetMapping("/list-journal/{myId}")
    public ResponseEntity<Object> byId(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry = journalEntryService.getAllbyId(myId);
        if (journalEntry.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "status", true,
                "message", "Journal entry fetched successfully",
                "status_code", 200,
                "data", journalEntry.get()
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                "status", false,
                "message", "Journal entry not found",
                "status_code", 404
            ));
        }
    }

    @DeleteMapping("/delete-journal/{myId}")
    public ResponseEntity<Object> deleteById(@PathVariable ObjectId myId) {
        boolean deleted = journalEntryService.deleteIfExists(myId);
        if (deleted) {
            return ResponseEntity.ok(Map.of(
                "status", true,
                "message", "Journal entry deleted successfully",
                "status_code", 200
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                "status", false,
                "message", "Journal entry not found",
                "status_code", 200
            ));
        }
    }
    
}
