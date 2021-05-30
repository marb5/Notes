package com.example.notes.controllers;

import com.example.notes.logic.BoxService;
import com.example.notes.model.BoxRead;
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
}
