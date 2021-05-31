package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.box.BoxRead;
import com.example.notes.model.BoxRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author marcin
 */
@Service
public class BoxService {
    private final BoxRepository repository;
    
    BoxService(BoxRepository boxRepository) {
        this.repository = boxRepository;
    }
    
    public List<BoxRead> findAllBoxes() {
        List<BoxRead> all = new ArrayList<>();
        repository.findAll().forEach((Box box) -> {
                            all.add(new BoxRead(box));
        });
        return all;
    }
    
    public BoxRead findBox(int id) {
        return new BoxRead(repository.findById(id));
    }
    
    public Box addBox(Box box) {
        return repository.save(box);
    }
    
    public Box updateBox(Box box, int id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        box.setId(id);
        return repository.save(box);
    }
    
    public String deleteBox(int id) {
        if (!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        repository.deleteById(id);
        return "Deleted";
    }
}
