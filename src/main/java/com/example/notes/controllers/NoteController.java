package com.example.notes.controllers;

import com.example.notes.model.NotePresent;
import com.example.notes.model.NoteRead;
import com.example.notes.logic.NoteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcin
 */
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;
    
    NoteController(NoteService noteService) {
        this.service = noteService;
    }
    
    @GetMapping
    ResponseEntity<List<NotePresent>> getAllNotes() {
        return new ResponseEntity<>(service.findAllNotes(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/{id}")
    ResponseEntity<NoteRead> getNotes(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findNoteById(id), HttpStatus.OK);
    }
}
