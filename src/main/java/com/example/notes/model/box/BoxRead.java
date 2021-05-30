package com.example.notes.model.box;

import com.example.notes.model.note.NotePresent;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author marcin
 */
public class BoxRead {
    private String name;
    private Set<NotePresent> notes;
    
    public BoxRead() {
    }

    public BoxRead(Box box) {
        this.name = box.getName();
        notes = box.getNotes().stream()
                            .map(NotePresent::new)
                            .collect(Collectors.toSet());
        
    }
    
    public BoxRead(Optional<Box> box) {
        if (box.isPresent()) {
            this.name = box.get().getName();
            notes = box.get().getNotes().stream()
                            .map(NotePresent::new)
                            .collect(Collectors.toSet());
        }
    }

    public String getName() {
        return name;
    }

    public Set<NotePresent> getNotes() {
        return notes;
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
        final BoxRead other = (BoxRead) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }
}
