package com.example.notes.model;

import com.example.notes.model.box.Box;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author marcin
 */
public interface BoxRepository extends JpaRepository<Box, Integer> {
    @Query("select distinct box from Box box join fetch box.notes")
    public List<Box> findAll();
    public Optional<Box> findById(Integer id);
    public boolean existsById(Integer id);
    public Box save(Box box);
    public void deleteById(Integer id);
}
