package com.wonder.wandernote.ui.notes.interfaces;

public interface AddNoteMvvmView {
    void savedNoteSuccess();

    void savedNoteFiled(String message);

    void startAddNote();

    void goBack();

    void showMaxLengthWarn(String string);
}
