package com.example.notes.model;

import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author marcin
 */
public class NoteRead {
    private int id;
    private String name;
    private String content;

    public NoteRead() {
    }

    public NoteRead(Optional<Note> note) {
        this();
        if (note.isPresent()) {
            this.id = note.get().getId();
            this.name = note.get().getName();
            this.content = note.get().getContent();
        }
    }
    
    public NoteRead(Note note) {
        this.id = note.getId();
        this.name = note.getName();
        this.content = note.getContent();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NoteRead other = (NoteRead) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    } 
}
