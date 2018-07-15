package com.wonder.wandernote.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<I> extends ViewModel {

    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private CompositeDisposable mCompositeDisposable;
    private I mvvmView;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }


    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public I getMvvmView() {
        return mvvmView;
    }

    public void setMvvmView(I mInterface) {
        mvvmView = mInterface;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

}
