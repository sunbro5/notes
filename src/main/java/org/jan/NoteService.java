package org.jan;

import org.jan.model.Note;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final Map<String, Note> notes = new ConcurrentHashMap<>();

    public Note getNoteById(String id) {
        return notes.get(id);
    }

    public void addNote(String id, Note note) {
        notes.put(id, note);
    }

}
