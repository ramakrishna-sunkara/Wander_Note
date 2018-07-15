package com.wonder.wandernote.ui.notes.viewModel;

import android.databinding.ObservableField;

import com.wonder.wandernote.model.Note;
import com.wonder.wandernote.utils.DateUtils;

public class ItemNoteViewModel {

    public final ObservableField<String> title;
    public final ObservableField<String> date;

    public ItemNoteViewModel(Note note) {
        title = new ObservableField<>(note.getNoteContent());
        date = new ObservableField<>(DateUtils.formatCreatedDate(note.getCreatedDate()));
    }
}
