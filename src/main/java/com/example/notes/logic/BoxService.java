package com.example.notes.logic;

import com.example.notes.model.Box;
import com.example.notes.model.BoxRead;
import com.example.notes.model.BoxRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

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
}
