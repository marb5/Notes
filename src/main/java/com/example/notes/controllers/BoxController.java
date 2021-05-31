package com.example.notes.controllers;

import com.example.notes.logic.BoxService;
import com.example.notes.model.box.Box;
import com.example.notes.model.box.BoxRead;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marcin
 */
@RestController
@RequestMapping("/boxes")
public class BoxController {
    private final BoxService service;
    
    BoxController(BoxService boxService) {
        this.service = boxService;
    }
    
    @GetMapping
    ResponseEntity<List<BoxRead>> getAllBoxes() {
        return new ResponseEntity<>(service.findAllBoxes(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    ResponseEntity<BoxRead> getBoxById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.findBox(id), HttpStatus.OK);
    }
    
    @Transactional
    @PostMapping
    public ResponseEntity<?> createBox(@Valid @RequestBody Box box, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(service.addBox(box), HttpStatus.CREATED);
    }
    
    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBox(@Valid @RequestBody Box box, BindingResult bindingResult,
                                        @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(bindingResult.getFieldError(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(service.updateBox(box, id), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    ResponseEntity<String> deleteNote(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.deleteBox(id), HttpStatus.OK);
    }
}
