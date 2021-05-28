package com.example.notes.logic;

import com.example.notes.model.Note;
import com.example.notes.model.NotePresent;
import com.example.notes.model.NoteRead;
import com.example.notes.model.NoteRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

/**
 *
 * @author marcin
 */
public class NoteServiceTest {
    private Note note1, note2;
    private ArrayList<Note> list;
    
    @BeforeEach
    void initTestData() {
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
    
    @Test
    @DisplayName("should return empty list")
    void findAllNotes_emptyRepository_shouldReturnEmptyList() {
        //given
        NoteRepository mockNoteRepository = Mockito.mock(NoteRepository.class);
        when(mockNoteRepository.findAll()).thenReturn(new ArrayList<Note>());
        //system under test
        NoteService service = new NoteService((com.example.notes.model.NoteRepository) mockNoteRepository);
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
        ArrayList<NotePresent> listOfPresentedNotes = new ArrayList<>();
        list.forEach((Note note) -> {
            listOfPresentedNotes.add(new NotePresent(note));
        });
        //system under test
        NoteService service = new NoteService(mockNoteRepository);
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
        //system under test
        NoteService service = new NoteService((com.example.notes.model.NoteRepository) mockNoteRepository);
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
        NoteRead note = new NoteRead(Optional.of(note2));
        //system under test
        NoteService service = new NoteService(mockNoteRepository);
        //when
        NoteRead result = service.findNoteById(2);
        //then
        assertThat(result).isEqualTo(note);
    }
}
