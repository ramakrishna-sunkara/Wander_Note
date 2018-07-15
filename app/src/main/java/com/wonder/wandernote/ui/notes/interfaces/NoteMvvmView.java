package com.wonder.wandernote.ui.notes.interfaces;

import java.util.List;

import com.wonder.wandernote.model.Note;

public interface NoteMvvmView {
    void loadNotesList(List<Note> moviesList);
}
