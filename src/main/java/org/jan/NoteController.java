package org.jan;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.jan.model.Note;

@Controller
public class NoteController {

    @Inject
    private NoteService noteService;

    @Get("/notes/{id}")
    public Note getNoteById(String id) {
        return noteService.getNoteById(id);
    }

    @Post("/notes/{id}")
    public void addNote(String id, @Body Note note) {
        noteService.addNote(id, note);
    }


}
