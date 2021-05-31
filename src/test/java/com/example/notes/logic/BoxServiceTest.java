package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.box.BoxRead;
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
import org.springframework.web.server.ResponseStatusException;

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
    
    @Test
    @DisplayName("should throw error \"Lack of box name!\" ")
    void addBox_emptyName_shouldThrowErrorLackName() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        Box json = new Box();
        json.setName("");
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        try {
            service.addBox(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of box name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of box name!\" ")
    void addBox_whitespacesName_shouldThrowErrorLackName() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        Box json = new Box();
        json.setName("       ");
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        try {
            service.addBox(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of box name!\"");
        }
    }
    
    @Test
    @DisplayName("should return new Box")
    void addBox_properName_shouldReturnRightBox() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        Box json = new Box();
        json.setName("Box3");
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        Box result = service.addBox(json);
        //then
        assertThat(result).isEqualTo(json);
        assertThat(service.findBox(3)).isEqualTo(new BoxRead(result));
    }
    
    @Test
    @DisplayName("should throw error \"Lack of box name!\" ")
    void updateBox_emptyName_shouldThrowErrorLackName() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        Box json = new Box();
        json.setName("");
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        try {
            service.updateBox(json, 2);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of box name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of box name!\" ")
    void updateBox_whitespacesName_shouldThrowErrorLackName() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        Box json = new Box();
        json.setName("       ");
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        try {
            service.updateBox(json, 2);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of box name!\"");
        }
    }
    
    @Test
    @DisplayName("should return new Box")
    void updateBox_properName_shouldReturnRightBox() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        Box json = new Box();
        json.setName("Box3");
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        Box result = service.updateBox(json, 2);
        //and
        BoxRead searched = service.findBox(2);
        //then
        assertThat(searched).isEqualTo(new BoxRead(result));
    }
    
    @Test
    @DisplayName("should throw NOT_FOUND")
    void deleteNote_indexOutOfRange_shouldThrowNotFound() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        try {
            service.deleteBox(3);
        } catch (Exception e) {
            //then
            assertThat(e).hasMessage("404 NOT_FOUND");
        }
    }
    
    @Test
    @DisplayName("should response with ok status")
    void deleteNote_deleteElement_shouldResponseOk() {
        //given
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //system under test
        BoxService service = new BoxService(mockBoxRepository);
        //when
        String result = service.deleteBox(1);
        //then
        assertThat(result).isEqualTo("Deleted");
        assertThat(mockBoxRepository.count()).isEqualTo(1);
    }
}
