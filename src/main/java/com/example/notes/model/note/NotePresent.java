package com.example.notes.model.note;

import java.util.Objects;

/**
 *
 * @author marcin
 */
public class NotePresent {
    private int id;
    private String name;

    public NotePresent(Note note) {
        this.id = note.getId();
        this.name = note.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
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
        final NotePresent other = (NotePresent) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
}
