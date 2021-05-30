package com.example.notes.logic;

import com.example.notes.model.box.Box;
import com.example.notes.model.BoxRepository;
import com.example.notes.model.note.Note;
import com.example.notes.model.note.NotePresent;
import com.example.notes.model.note.NoteRead;
import com.example.notes.model.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author marcin
 */
public class NoteServiceTest {
    private Box box1, box2;
    private Note note1, note2;
    private ArrayList<Note> list;
    
    @BeforeEach
    void initTestData() {
        box1 = new Box();
        box1.setId(1);
        box1.setName("Box1");
        box2 = new Box();
        box2.setId(2);
        box2.setName("Box2");
        note1 = new Note();
        note1.setId(1);
        note1.setName("Note1");
        note1.setContent("Note1's content");
        note1.setBox(box1);
        note2 = new Note();
        note2.setId(2);
        note2.setName("Note2");
        note2.setContent("Note2's content");
        note2.setBox(box1);
        list = new ArrayList<>() {
                        {
                            add(note1);
                            add(note2);
                        }
                    };
    }
    
    @Test
    @DisplayName("should return empty list")
    void findAllNotes_emptyRepository_shouldReturnEmptyList() {
        //given
        NoteRepository mockNoteRepository = Mockito.mock(NoteRepository.class);
        when(mockNoteRepository.findAll()).thenReturn(new ArrayList<Note>());
        //and
        BoxRepository mockBoxRepository = Mockito.mock(BoxRepository.class);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        List<NotePresent> resultList = service.findAllNotes();
        //then
        assertThat(resultList).isEmpty();
    }
    
    @Test
    @DisplayName("should return list of NotePresent dtos")
    void findAllNotePresentObjects_mockedRepository_shouldReturnListOfSameObjects() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        ArrayList<NotePresent> listOfPresentedNotes = new ArrayList<>();
        list.forEach((Note note) -> {
            listOfPresentedNotes.add(new NotePresent(note));
        });
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        List<NotePresent> resultList = service.findAllNotes();
        //then
        assertThat(resultList).isEqualTo(listOfPresentedNotes);
    }
    
    @Test
    @DisplayName("should return empty dto")
    void findNoteById_emptyRepository_shouldReturnEmptyDTO() {
        //given
        NoteRepository mockNoteRepository = Mockito.mock(NoteRepository.class);
        when(mockNoteRepository.findById(anyInt())).thenReturn(Optional.of(new Note()));
        //and
        BoxRepository mockBoxRepository = Mockito.mock(BoxRepository.class);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead result = service.findNoteById(1);
        //then
        assertThat(result).isEqualTo(new NoteRead(Optional.of(new Note())));
    }
    
    @Test
    @DisplayName("should return same NoteRead object")
    void findNoteById_mockedRepository_shouldSameDTO() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        NoteRead note = new NoteRead(Optional.of(note2));
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead result = service.findNoteById(2);
        //then
        assertThat(result).isEqualTo(note);
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note name!\" ")
    void addNewNote_nullName_shouldThrowErrorLackName() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note name!\" ")
    void addNewNote_emptyName_shouldThrowErrorLackName() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "");
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note name!\" ")
    void addNewNote_whitespacesName_shouldThrowErrorLackName() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "      ");
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note content!\" ")
    void addNewNote_nullContent_shouldThrowErrorLackContent() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note content!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note content!\" ")
    void addNewNote_emptyContent_shouldThrowErrorLackContent() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note content!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note content!\" ")
    void addNewNote_whitespacesContent_shouldThrowErrorLackContent() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "                   ");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note content!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of box id!\" ")
    void addNewNote_noBoxIdInJSON_shouldThrowErrorLackBoxId() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of box id!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Box id should be greater than 0!\" ")
    void addNewNote_zeroBoxIdValue_shouldThrowErrorBoxIdNotPositive() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", 0);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Box id should be greater than 0!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Box id should be greater than 0!\" ")
    void addNewNote_negativeBoxIdValue_shouldThrowErrorBoxIdNotPositive() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", -1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            assertThat(e).hasMessage("400 BAD_REQUEST \"Box id should be greater than 0!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Wrong box id!\" ")
    void addNewNote_emptyStringBoxId_shouldThrowErrorWrongBoxId() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", "");
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessageContaining("400 BAD_REQUEST \"Wrong box id!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Wrong box id!\" ")
    void addNewNote_stringBoxId_shouldThrowErrorWrongBoxId() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", "q");
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.addNewNote(json);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessageContaining("400 BAD_REQUEST \"Wrong box id!\"");
        }
    }
    
    @Test
    @DisplayName("should return DTO with saved entity")
    void addNewNote_everythingsRight_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //and
        Note newNote = new Note();
        newNote.setId(3);
        newNote.setName("Note3");
        newNote.setContent("Content for Note3");
        newNote.setBox(box1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.addNewNote(json);
        //and
        NoteRead result = service.findNoteById(3);
        //then
        assertThat(dto).isEqualTo(new NoteRead(newNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(newNote));
    }
    
    @Test
    @DisplayName("should throw \"404 NOT_FOUND\"")
    void updateNote_noteWithIdNotExists_shouldThrowErrorNotFound() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 3;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (Exception e) {
            //then
            assertThat(e).hasMessage("404 NOT_FOUND");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note name!\" ")
    void updateNote_emptyName_shouldThrowErrorLackName() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "");
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note name!\" ")
    void updateNote_whitespacesName_shouldThrowErrorLackName() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "      ");
        json.put("content", "Content for Note3");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note name!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note content!\" ")
    void updateNote_emptyContent_shouldThrowErrorLackContent() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note content!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Lack of note content!\" ")
    void updateNote_whitespacesContent_shouldThrowErrorLackContent() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "                   ");
        json.put("boxId", 1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Lack of note content!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Box id should be greater than 0!\" ")
    void updateNote_zeroBoxIdValue_shouldThrowErrorBoxIdNotPositive() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", 0);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessage("400 BAD_REQUEST \"Box id should be greater than 0!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Box id should be greater than 0!\" ")
    void updateNote_negativeBoxIdValue_shouldThrowErrorBoxIdNotPositive() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", -1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            assertThat(e).hasMessage("400 BAD_REQUEST \"Box id should be greater than 0!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Wrong box id!\" ")
    void updateNote_emptyStringBoxId_shouldThrowErrorWrongBoxId() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", "");
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessageContaining("400 BAD_REQUEST \"Wrong box id!\"");
        }
    }
    
    @Test
    @DisplayName("should throw error \"Wrong box id!\" ")
    void updateNote_stringBoxId_shouldThrowErrorWrongBoxId() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", "q");
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.updateNote(json, id);
        }
        catch (ResponseStatusException e) {
            //then
            assertThat(e).hasMessageContaining("400 BAD_REQUEST \"Wrong box id!\"");
        }
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_everythingsRight_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        json.put("boxId", 2);
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note3");
        updatedNote.setContent("Content for Note3");
        updatedNote.setBox(box2);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_jsonContainsNameAndContent_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("content", "Content for Note3");
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note3");
        updatedNote.setContent("Content for Note3");
        updatedNote.setBox(box1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_jsonContainsNameAndBoxId_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        json.put("boxId", 2);
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note3");
        updatedNote.setContent("Note2's content");
        updatedNote.setBox(box2);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_jsonContainsContentAndBoxId_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("content", "Content for Note3");
        json.put("boxId", 2);
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note2");
        updatedNote.setContent("Content for Note3");
        updatedNote.setBox(box2);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_onlyName_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("name", "Note3");
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note3");
        updatedNote.setContent("Note2's content");
        updatedNote.setBox(box1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_onlyContent_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("content", "Content for Note3");
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note2");
        updatedNote.setContent("Content for Note3");
        updatedNote.setBox(box1);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should return DTO with updated entity")
    void updateNote_onlyBoxId_allShouldBeSame() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //and
        int id = 2;
        //and
        JSONObject json = new JSONObject();
        json.put("boxId", 2);
        //and
        Note updatedNote = new Note();
        updatedNote.setId(id);
        updatedNote.setName("Note2");
        updatedNote.setContent("Note2's content");
        updatedNote.setBox(box2);
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        NoteRead dto = service.updateNote(json, id);
        //and
        NoteRead result = service.findNoteById(id);
        //then
        assertThat(dto).isEqualTo(new NoteRead(updatedNote));
        //and
        assertThat(result).isEqualTo(new NoteRead(updatedNote));
    }
    
    @Test
    @DisplayName("should throw NOT_FOUND")
    void deleteNote_indexOutOfRange_shouldThrowNotFound() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        try {
            service.deleteNote(3);
        } catch (Exception e) {
            //then
            assertThat(e).hasMessage("404 NOT_FOUND");
        }
    }
    
    @Test
    @DisplayName("should response with ok status")
    void deleteNote_deleteElement_shouldResponseOk() {
        //given
        NoteRepository mockNoteRepository = (new MockNoteRepository()).init();
        //and
        BoxRepository mockBoxRepository = (new MockBoxRepository()).init();
        //system under test
        NoteService service = new NoteService(mockNoteRepository, mockBoxRepository);
        //when
        String result = service.deleteNote(1);
        //then
        assertThat(result).isEqualTo("Deleted");
    }
}
