package com.example.notes.logic;

import com.example.notes.model.Note;
import com.example.notes.model.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author marcin
 */
public class MockNoteRepository {
    Note note1, note2;
    ArrayList<Note> list;
    int newIndex = 3;

    MockNoteRepository() {
        note1 = new Note();
        note1.setId(1);
        note1.setName("Note1");
        note1.setContent("Note1's content");
        note1.setBoxId(1);
        note2 = new Note();
        note2.setId(2);
        note2.setName("Note2");
        note2.setContent("Note2's content");
        note2.setBoxId(1);
        list = new ArrayList<>() {
                        {
                            add(note1);
                            add(note2);
                        }
                    };
    }
    
    NoteRepository init() {
        return new NoteRepository() {
            @Override
            public List<Note> findAll() {
                return list;
            }

            @Override
            public Optional<Note> findById(Integer id) {
                for (int i = 0 ; i < list.size() ; ++i)
                    if (list.get(i).getId() == id)
                        return Optional.of(list.get(i));
                return null;
            }
            
            @Override
            public Note save(Note note) {
                note.setId(newIndex++);
                list.add(note);
                return note;
            }

            @Override
            public List<Note> findAll(Sort arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Note> findAllById(Iterable<Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> saveAll(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> S saveAndFlush(S arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> saveAllAndFlush(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllInBatch(Iterable<Note> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllByIdInBatch(Iterable<Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllInBatch() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Note getOne(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Note getById(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> findAll(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> List<S> findAll(Example<S> arg0, Sort arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Page<Note> findAll(Pageable arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean existsById(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public long count() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteById(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void delete(Note arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll(Iterable<? extends Note> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> Optional<S> findOne(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> Page<S> findAll(Example<S> arg0, Pageable arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> long count(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Note> boolean exists(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    }
}
