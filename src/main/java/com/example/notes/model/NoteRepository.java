package com.example.notes.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author marcin
 */
@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note, Integer> {
    public List<Note> findAll();
    public Optional<Note> findById(Integer id);
}
