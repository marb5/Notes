package com.example.notes.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcin
 */
public interface BoxRepository extends JpaRepository<Box, Integer> {
    public List<Box> findAll();
    public Optional<Box> findById(Integer id);
    public boolean existsById(Integer id);
}
