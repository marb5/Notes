package com.example.notes.logic;

import com.example.notes.model.Note;
import com.example.notes.model.NotePresent;
import com.example.notes.model.NoteRead;
import com.example.notes.model.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    
    public NoteRead addNewNote(JSONObject json) {
        Note newNote = new Note();
        //note name
        if (json.get("name") != null && !json.get("name").toString().isBlank())
            newNote.setName(json.get("name").toString());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note name!");
        //note content
        if (json.get("content") != null && !json.get("content").toString().isBlank())
            newNote.setContent(json.get("content").toString());
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note content!");
        //note box_id
        if (json.get("boxId") != null) {
            try {
                newNote.setBoxId(Integer.parseInt(json.get("boxId").toString()));
                if (newNote.getBoxId() < 1)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Box id should be greater than 0!");
            }
            catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong box id!", e);
            }
        }
        else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of box id!");
        
        return new NoteRead(repository.save(newNote));
    }
}
