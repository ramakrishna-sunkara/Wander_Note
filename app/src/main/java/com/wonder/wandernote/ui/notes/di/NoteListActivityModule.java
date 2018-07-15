package com.wonder.wandernote.ui.notes.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import com.wonder.wandernote.ViewModelProviderFactory;
import com.wonder.wandernote.services.RetrofitService;
import com.wonder.wandernote.ui.notes.viewModel.NoteViewModel;
import com.wonder.wandernote.utils.ResourceProvider;
import retrofit2.Retrofit;


@Module
public class NoteListActivityModule {


    @Provides
    RetrofitService provideApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Provides
    NoteViewModel provideNoteViewModel(RetrofitService retrofitService, ResourceProvider
            resourceProvider) {
        return new NoteViewModel(retrofitService, resourceProvider);
    }

    @Provides
    ViewModelProvider.Factory noteViewModelProvider(NoteViewModel noteViewModel) {
        return new ViewModelProviderFactory<>(noteViewModel);
    }


}
