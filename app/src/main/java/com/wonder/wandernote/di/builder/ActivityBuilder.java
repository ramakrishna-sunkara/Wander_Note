package com.wonder.wandernote.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.wonder.wandernote.ui.notes.AddNoteActivity;
import com.wonder.wandernote.ui.notes.NoteActivity;
import com.wonder.wandernote.ui.notes.di.AddNoteActivityModule;
import com.wonder.wandernote.ui.notes.di.NoteListActivityModule;


@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            NoteListActivityModule.class})
    abstract NoteActivity bindNoteActivity();

    @ContributesAndroidInjector(modules = {
            AddNoteActivityModule.class})
    abstract AddNoteActivity bindAddNoteActivity();

}
