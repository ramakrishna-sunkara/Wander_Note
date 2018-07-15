package com.wonder.wandernote.ui.notes.viewModel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import com.wonder.wandernote.R;
import com.wonder.wandernote.services.RetrofitService;
import com.wonder.wandernote.ui.base.BaseViewModel;
import com.wonder.wandernote.ui.notes.interfaces.AddNoteMvvmView;
import com.wonder.wandernote.utils.AppConstants;
import com.wonder.wandernote.utils.ResourceProvider;

public class AddNoteViewModel extends BaseViewModel<AddNoteMvvmView> {
    public final ObservableField<String> status;
    public final ObservableField<String> noteContent;
    public final ObservableField<String> noteLength;
    public final ObservableBoolean isSaveButtonShow = new ObservableBoolean(false);
    private RetrofitService retrofitService;
    private ResourceProvider mResourceProvider;


    public AddNoteViewModel(RetrofitService service,
                            ResourceProvider resourceProvider) {
        super();
        retrofitService = service;
        mResourceProvider = resourceProvider;
        status = new ObservableField<>(mResourceProvider.getString(R.string.no_results));
        noteContent = new ObservableField<>(mResourceProvider.getString(R.string.empty_str));
        noteLength = new ObservableField<>(mResourceProvider.getString(R.string.empty_str));
        noteLength.set(mResourceProvider.getString(R.string.label_note_length, String.valueOf(0)));
    }

    public void saveNote(String content) {
        setIsLoading(true);
        noteContent.set(content);
        getCompositeDisposable().add(retrofitService.addNote(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(addNoteResponse -> {
                    if (addNoteResponse.isSuccess()) {
                        getMvvmView().savedNoteSuccess();
                    } else {
                        getMvvmView().savedNoteFiled(addNoteResponse.getMessage());
                    }
                    setIsLoading(false);
                }, throwable -> {
                    getMvvmView().savedNoteFiled(throwable.getMessage());
                    setIsLoading(false);
                }));

    }

    public void onSaveIconClick(View view) {
        getMvvmView().startAddNote();
    }

    public void onBackIconClick(View view) {
        getMvvmView().goBack();
    }


    public boolean isNoteValid(String note) {
        return !TextUtils.isEmpty(note);
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        noteContent.set(s.toString());
        if (s.length() > 0) {
            isSaveButtonShow.set(true);
        } else {
            isSaveButtonShow.set(false);
        }
        noteLength.set(mResourceProvider.getString(R.string.label_note_length, String.valueOf(s.length())));
        if (s.length() >= AppConstants.NOTE_MAX_LENGTH) {
            getMvvmView().showMaxLengthWarn(mResourceProvider.getString(R.string.label_note_max_length_reached));
        }
    }
}
