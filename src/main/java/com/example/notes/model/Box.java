package com.example.notes.model;

import com.example.notes.model.note.Note;
import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author marcin
 */
@Entity
@Table(name = "boxes")
public class Box implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "box")
    private Set<Note> notes;

    public Box() {
    }

    public Box(Optional<Box> box) {
        if (box.isPresent()) {
            this.id = box.get().getId();
            this.name = box.get().getName();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Note> getNotes() {
        return notes;
    }

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
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
        final Box other = (Box) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }
}
