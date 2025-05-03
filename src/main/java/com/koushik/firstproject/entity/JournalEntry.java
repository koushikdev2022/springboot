package com.koushik.firstproject.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

@Document(collection = "journal_entries")
public class JournalEntry {
    @Id
    private ObjectId id;

    private String title;
    private String content;

    // Constructors
    public JournalEntry() {
    }

    public JournalEntry(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter and Setter for id
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    // Getter and Setter for title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter and Setter for content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
