package com.wonder.wandernote.ui.notes;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import com.wonder.wandernote.BR;
import com.wonder.wandernote.R;
import com.wonder.wandernote.databinding.ActivityAddnoteBinding;
import com.wonder.wandernote.ui.base.BaseActivity;
import com.wonder.wandernote.ui.notes.interfaces.AddNoteMvvmView;
import com.wonder.wandernote.ui.notes.viewModel.AddNoteViewModel;

public class AddNoteActivity extends BaseActivity<ActivityAddnoteBinding, AddNoteViewModel> implements
        AddNoteMvvmView {

    @Inject
    AddNoteViewModel mAddNoteViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;
    private ActivityAddnoteBinding mActivityAddNoteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAddNoteBinding = getViewDataBinding();

        mAddNoteViewModel.setMvvmView(this);
        setSupportActionBar(mActivityAddNoteBinding.toolbar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_addnote;
    }

    @Override
    public AddNoteViewModel getViewModel() {
        mAddNoteViewModel = ViewModelProviders.of(this, mViewModelFactory).get(AddNoteViewModel.class);
        return mAddNoteViewModel;
    }

    @Override
    public int getBindingVariable() {
        return BR.addNoteViewModel;
    }

    @Override
    public void savedNoteSuccess() {
        Toast.makeText(getApplication(), getString(R.string.note_saved_success), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void savedNoteFiled(String message) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startAddNote() {
        hideSoftKeyboard(this);
        String note = mActivityAddNoteBinding.edittextAddNote.getText().toString().trim();
        if (isNetworkConnected()) {
            if (mAddNoteViewModel.isNoteValid(note)) {
                mAddNoteViewModel.saveNote(note);
            } else {
                Toast.makeText(getApplication(), R.string.note_is_empty, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplication(), R.string.network_not_aviable, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void showMaxLengthWarn(String string) {
        Toast.makeText(getApplication(), string, Toast.LENGTH_LONG).show();
    }

}
