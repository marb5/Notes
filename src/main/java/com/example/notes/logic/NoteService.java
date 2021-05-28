package com.example.notes.logic;

import com.example.notes.model.Note;
import com.example.notes.model.NotePresent;
import com.example.notes.model.NoteRead;
import com.example.notes.model.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author marcin
 */
@Service
public class NoteService {
    private final NoteRepository repository;
    
    NoteService(NoteRepository noteRepository) {
        this.repository = noteRepository;
    }
    
    public List<NotePresent> findAllNotes() {
        List<NotePresent> all = new ArrayList<>();
        repository.findAll().stream()
                        .collect(Collectors.toList())
                        .forEach((Note note) -> {
                            all.add(new NotePresent(note));
        });
        return all;
    }
    
    public NoteRead findNoteById(int id) {
        return new NoteRead(repository.findById(id));
    }
}
