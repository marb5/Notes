package com.example.notes;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author marcin
 */
@RepositoryRestResource
public interface NoteRepository extends JpaRepository<Note, Integer> {
    
    public List<Note> findAll();
}
