package com.example.notes.model.note;

import com.example.notes.model.box.Box;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author marcin
 */
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String content;
    @ManyToOne
    @JoinColumn(name = "box_id", referencedColumnName = "id")
    private Box box;

    public Note() {
    }

    public Note(Optional<Note> note) {
        this();
        if (note.isPresent()) {
            this.id = note.get().getId();
            this.name = note.get().getName();
            this.content = note.get().getContent();
            this.box = note.get().getBox();
        }
    }

    public Note(String name, String content, Optional<Box> box) {
        this();
        if (box.isPresent()) {
            this.name = name;
            this.content = content;
            this.box = box.get();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
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
        final Note other = (Note) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.box, other.box)) {
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
