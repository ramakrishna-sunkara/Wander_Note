package com.wonder.wandernote.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.wonder.wandernote.databinding.ItemNoteBinding;
import com.wonder.wandernote.model.Note;
import com.wonder.wandernote.ui.notes.viewModel.ItemNoteViewModel;
import com.wonder.wandernote.ui.notes.viewModel.NoteViewModel;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesHolder> {

    NoteViewModel mNoteViewModel;
    private List<Note> noteList = new ArrayList<>();

    public NotesAdapter(NoteViewModel noteViewModel) {
        this.mNoteViewModel = noteViewModel;
    }

    @NonNull
    @Override
    public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding itemNoteBinding = ItemNoteBinding.inflate(LayoutInflater.from(parent
                        .getContext()),
                parent, false);
        return new NotesHolder(itemNoteBinding);
    }


    @Override
    public void onBindViewHolder(NotesHolder holder, int position) {
        holder.bindMovie(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void addNotesToAdapter(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public class NotesHolder extends RecyclerView.ViewHolder {
        ItemNoteBinding itemNoteBinding;

        public NotesHolder(ItemNoteBinding itemNoteBinding) {
            super(itemNoteBinding.itemMovie);
            this.itemNoteBinding = itemNoteBinding;
        }


        void bindMovie(Note note) {
            ItemNoteViewModel itemNoteViewModel = new ItemNoteViewModel(note);
            itemNoteBinding.setNoteItemViewModel(itemNoteViewModel);
            itemNoteBinding.executePendingBindings();
        }
/*
        @Override
        public void onAddFavouriteClick(Movie movie) {
            mMainViewModel.insertFavouriteMovie(movie);
        }*/
    }

    /*public void clearMovies() {
        moviesList.clear();
        notifyDataSetChanged();
    }*/
}
