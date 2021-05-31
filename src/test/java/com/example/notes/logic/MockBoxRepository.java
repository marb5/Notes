package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.BoxRepository;
import com.example.notes.model.note.Note;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author marcin
 */
public class MockBoxRepository {
    Box box1, box2;
    ArrayList<Box> list;
    int newIndex = 3;

    MockBoxRepository() {
        box1 = new Box();
        box1.setId(1);
        box1.setName("Box1");
        box1.setNotes(new HashSet<Note>());
        box2 = new Box();
        box2.setId(2);
        box2.setName("Box2");
        box2.setNotes(new HashSet<Note>());
        list = new ArrayList<>() {
                        {
                            add(box1);
                            add(box2);
                        }
                    };
    }
    
    BoxRepository init() {
        return new BoxRepository() {
            public long count() {
                return list.size();
            }
            
            @Override
            public List<Box> findAll() {
                return list;
            }

            @Override
            public Optional<Box> findById(Integer id) {
                for (int i = 0 ; i < list.size() ; ++i)
                    if (list.get(i).getId() == id)
                        return Optional.of(list.get(i));
                return null;
            }
            
            @Override
            public boolean existsById(Integer id) {
                for (int i = 0; i < list.size(); ++i)
                    if (list.get(i).getId() == id)
                        return true;
                return false;
            }
            
            @Override
            public Box save(Box box) {
                for (int i = 0; i < list.size(); ++i)
                    if (list.get(i).getId() == box.getId()) {
                        list.get(i).setName(box.getName());
                        return list.get(i);
                    }
                box.setId(newIndex++);
                box.setNotes(new HashSet<Note>());
                list.add(box);
                return box;
            }
            
            @Override
            public void deleteById(Integer id) {
                for (int i = 0; i < list.size(); ++i) {
                    if (list.get(i).getId() == id)
                       list.remove(list.get(i));
                }
            }

            @Override
            public List<Box> findAll(Sort arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public List<Box> findAllById(Iterable<Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> List<S> saveAll(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void flush() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> S saveAndFlush(S arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> List<S> saveAllAndFlush(Iterable<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllInBatch(Iterable<Box> arg0) {
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
            public Box getOne(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Box getById(Integer arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> List<S> findAll(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> List<S> findAll(Example<S> arg0, Sort arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Page<Box> findAll(Pageable arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void delete(Box arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAllById(Iterable<? extends Integer> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll(Iterable<? extends Box> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void deleteAll() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> Optional<S> findOne(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> Page<S> findAll(Example<S> arg0, Pageable arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> long count(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public <S extends Box> boolean exists(Example<S> arg0) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        };
    }
}
