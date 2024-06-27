package org.jan;

import jakarta.inject.Singleton;
import org.jan.model.Note;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class NoteService {

    private final Map<String, Note> notes = new ConcurrentHashMap<>();

    public Note getNoteById(String id) {
        return notes.get(id);
    }

    public void addNote(String id, Note note) {
        notes.put(id, note);
    }

}
