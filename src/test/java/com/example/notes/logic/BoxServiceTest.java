package com.example.notes.logic;

import com.example.notes.model.Box;
import com.example.notes.model.BoxRead;
import com.example.notes.model.BoxRepository;
import com.example.notes.model.note.Note;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author marcin
 */
public class BoxServiceTest {
    private Box box1, box2;
    private ArrayList<Box> list;
    
    @BeforeEach
    void initTestData() {
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
    @Test
    @DisplayName("should return empty list")
    void findAllBoxes_emptyRepository_shouldReturnEmptyList() {
        //given
        BoxRepository mockBoxRepository = Mockito.mock(BoxRepository.class);
        when(mockBoxRepository.findAll()).thenReturn(new ArrayList<Box>());
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        List<BoxRead> resultList = service.findAllBoxes();
        //then
        assertThat(resultList).isEmpty();
    }
    
    @Test
    @DisplayName("should return list of NotePresent dtos")
    void findAllBoxes_mockedRepository_shouldReturnListOfSameObjects() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        ArrayList<BoxRead> listOfBoxes = new ArrayList<>();
        list.forEach((Box box) -> {
            listOfBoxes.add(new BoxRead(box));
        });
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        List<BoxRead> resultList = service.findAllBoxes();
        //then
        assertThat(resultList).isEqualTo(listOfBoxes);
    }
}