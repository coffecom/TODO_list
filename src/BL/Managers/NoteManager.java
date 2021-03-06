package BL.Managers;

import BL.JointNote;
import BL.Note;
import BL.User;

import java.util.ArrayList;

public interface NoteManager {
    void update(Note oldNote, Note newNote);

    void update(Note note);

    void add(Note note);

    void deleteNote(Note note);

    Note getNote(String noteId);

    String getNoteAuthor(Note note);

    ArrayList<Note> getAllNotesOfUser();

    ArrayList<Note> getUnnoticed();

    void setNoticed(JointNote note);
}
