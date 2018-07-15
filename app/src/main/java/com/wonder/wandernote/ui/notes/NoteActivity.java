package com.wonder.wandernote.ui.notes;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import com.wonder.wandernote.BR;
import com.wonder.wandernote.R;
import com.wonder.wandernote.adapters.NotesAdapter;
import com.wonder.wandernote.databinding.ActivityNoteBinding;
import com.wonder.wandernote.model.Note;
import com.wonder.wandernote.ui.base.BaseActivity;
import com.wonder.wandernote.ui.notes.interfaces.NoteMvvmView;
import com.wonder.wandernote.ui.notes.viewModel.NoteViewModel;

public class NoteActivity extends BaseActivity<ActivityNoteBinding, NoteViewModel> implements
        NoteMvvmView {

    @Inject
    NoteViewModel mNoteViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    NotesAdapter adapter;
    private ActivityNoteBinding mActivityNoteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityNoteBinding = getViewDataBinding();

        mNoteViewModel.setMvvmView(this);
        setSupportActionBar(mActivityNoteBinding.toolbar);
        setTitle(R.string.app_name);
        setAdapter(mActivityNoteBinding.recyclerview);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNoteViewModel.fetchMyNotes();
    }

    private void setAdapter(RecyclerView recyclerview) {
        adapter = new NotesAdapter(mNoteViewModel);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        if (mNoteViewModel.noteList != null && mNoteViewModel.noteList.size() > 0) {
            adapter.addNotesToAdapter(mNoteViewModel.noteList);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_note;
    }

    @Override
    public NoteViewModel getViewModel() {
        mNoteViewModel = ViewModelProviders.of(this, mViewModelFactory).get(NoteViewModel.class);
        return mNoteViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.noteViewModel;
    }

    @Override
    public void loadNotesList(List<Note> noteList) {
        adapter.addNotesToAdapter(noteList);
        mActivityNoteBinding.notifyPropertyChanged(BR.noteViewModel);
    }
}
