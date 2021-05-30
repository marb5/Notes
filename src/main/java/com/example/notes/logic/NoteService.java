package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.BoxRepository;
import com.example.notes.model.note.Note;
import com.example.notes.model.note.NotePresent;
import com.example.notes.model.note.NoteRead;
import com.example.notes.model.NoteRepository;
import com.example.notes.model.note.NoteWrite;
import java.util.ArrayList;
import java.util.List;
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
    private final BoxRepository boxRepository;
    
    NoteService(NoteRepository noteRepository, BoxRepository boxRepository) {
        this.repository = noteRepository;
        this.boxRepository = boxRepository;
    }
    
    public List<NotePresent> findAllNotes() {
        List<NotePresent> all = new ArrayList<>();
        repository.findAll()
                        .forEach((Note note) -> {
                            all.add(new NotePresent(note));
        });
        return all;
    }
    
    public NoteRead findNoteById(int id) {
        return new NoteRead(repository.findById(id));
    }
    
    public NoteRead addNewNote(JSONObject json) {
        NoteWrite newNote = new NoteWrite();
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
        
        Note note = new Note(newNote.getName(), newNote.getContent(), boxRepository.findById(newNote.getBoxId()));
        return new NoteRead(repository.save(note));
    }
    
    public NoteRead updateNote(JSONObject json, int id) {
        Note updateNote;
        try {
            updateNote = new Note(repository.findById(id));
            if (updateNote.equals(new Note()))
               throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //note name
        if (json.get("name") != null) {
            if (!json.get("name").toString().isBlank())
                updateNote.setName(json.get("name").toString());
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note name!");
        }
        //note content
        if (json.get("content") != null) {
            if (!json.get("content").toString().isBlank())
                updateNote.setContent(json.get("content").toString());
            else
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Lack of note content!");
        }
        //note box_id
        if (json.get("boxId") != null) {
            try {
                int boxId = Integer.parseInt(json.get("boxId").toString());
                if (boxId < 1)
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Box id should be greater than 0!");
                if (boxRepository.existsById(boxId));
                    updateNote.setBox(new Box(boxRepository.findById(boxId)));
            }
            catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong box id!", e);
            }
        }
        
        return new NoteRead(repository.save(updateNote));
    }
    
    public String deleteNote(int id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return "Deleted";
    }
}
