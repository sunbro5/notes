package org.jan;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jan.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteController noteController;

    @BeforeEach
    void setUp() {
        Note testNote = new Note("test");
        noteService.addNote("11", testNote);
    }

    @Test
    void testGetNoteById() throws Exception {

        MvcResult result = mockMvc.perform(get("/notes/11")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper mapper = new ObjectMapper();
        Note resultNote = mapper.readValue(result.getResponse().getContentAsString(), Note.class);

        assertEquals("test", resultNote.text());


    }


    @Test
    void testAddNote() throws Exception {
        Note newNote = new Note("New Note");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonNote = objectMapper.writeValueAsString(newNote);

        mockMvc.perform(post("/notes/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonNote)
        ).andExpect(status().isOk());

        Note resultNote = noteService.getNoteById("12");

        assertNotNull(resultNote);
        assertEquals("New Note", resultNote.text());
    }

}
