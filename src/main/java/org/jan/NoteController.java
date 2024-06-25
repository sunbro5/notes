package org.jan;

import lombok.RequiredArgsConstructor;
import org.jan.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NoteController {

    private final NoteService noteService;

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.GET)
    public Note getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id);
    }

    @RequestMapping(value = "/notes/{id}", method = RequestMethod.POST)
    public void addNote(@PathVariable String id, @RequestBody Note note) {
        noteService.addNote(id, note);
    }

}
