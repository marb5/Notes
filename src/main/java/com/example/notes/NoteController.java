package com.example.notes;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcin
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteRepository repository;
    
    NoteController(NoteRepository noteRepository) {
        this.repository = noteRepository;
    }
    
    @GetMapping
    ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
}
