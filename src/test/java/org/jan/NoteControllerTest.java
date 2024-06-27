package org.jan;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.serde.ObjectMapper;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.jan.model.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class NoteControllerTest {

    @Inject
    @Client("/")
    private HttpClient client;

    @Inject
    private NoteService noteService;

    @Inject
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        Note testNote = new Note("test");
        noteService.addNote("11", testNote);
    }

    @Test
    void testGetNoteById() throws Exception {
        String response = client.toBlocking().retrieve("/notes/11");
        Note resultNote = objectMapper.readValue(response, Note.class);

        assertEquals("test", resultNote.text());
    }

    @Test
    void testAddNote() throws Exception {
        Note newNote = new Note("New Note");
        String jsonNote = objectMapper.writeValueAsString(newNote);

        client.toBlocking().exchange(HttpRequest.POST("/notes/12", jsonNote)
                .contentType(MediaType.APPLICATION_JSON), String.class);
        Note resultNote = noteService.getNoteById("12");

        assertNotNull(resultNote);
        assertEquals("New Note", resultNote.text());
    }
}


