package com.wonder.wandernote.ui.notes.viewModel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import com.wonder.wandernote.R;
import com.wonder.wandernote.model.Note;
import com.wonder.wandernote.services.RetrofitService;
import com.wonder.wandernote.ui.base.BaseViewModel;
import com.wonder.wandernote.ui.notes.interfaces.NoteMvvmView;
import com.wonder.wandernote.utils.ResourceProvider;

public class NoteViewModel extends BaseViewModel<NoteMvvmView> {
    public final ObservableField<String> status;
    public final ObservableBoolean isListEmpty = new ObservableBoolean(false);
    public List<Note> noteList;
    private RetrofitService retrofitService;
    private ResourceProvider mResourceProvider;

    public NoteViewModel(RetrofitService service,
                         ResourceProvider resourceProvider) {
        super();
        retrofitService = service;
        mResourceProvider = resourceProvider;
        status = new ObservableField<>(mResourceProvider.getString(R.string.no_results));
    }

    public void fetchMyNotes() {
        setIsLoading(true);
        getCompositeDisposable().add(retrofitService.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notesResponse -> {
                    if (notesResponse.isSuccess() && notesResponse.getNotes() != null && notesResponse.getNotes().size() > 0) {
                        isListEmpty.set(false);
                        noteList = notesResponse.getNotes();
                        getMvvmView().loadNotesList(noteList);
                    } else {
                        status.set(mResourceProvider.getString(R.string.no_results));
                        isListEmpty.set(true);
                    }
                    setIsLoading(false);
                }, throwable -> {
                    status.set(throwable.getMessage());
                    setIsLoading(false);
                }));

    }
}
