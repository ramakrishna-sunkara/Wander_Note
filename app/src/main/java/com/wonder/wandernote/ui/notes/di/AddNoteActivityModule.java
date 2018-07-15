package com.wonder.wandernote.ui.notes.di;

import android.arch.lifecycle.ViewModelProvider;

import dagger.Module;
import dagger.Provides;
import com.wonder.wandernote.ViewModelProviderFactory;
import com.wonder.wandernote.services.RetrofitService;
import com.wonder.wandernote.ui.notes.viewModel.AddNoteViewModel;
import com.wonder.wandernote.utils.ResourceProvider;
import retrofit2.Retrofit;


@Module
public class AddNoteActivityModule {

    @Provides
    RetrofitService provideApiService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Provides
    AddNoteViewModel provideAddNoteViewModel(RetrofitService retrofitService, ResourceProvider
            resourceProvider) {
        return new AddNoteViewModel(retrofitService, resourceProvider);
    }

    @Provides
    ViewModelProvider.Factory addNoteViewModelProvider(AddNoteViewModel addNoteViewModel) {
        return new ViewModelProviderFactory<>(addNoteViewModel);
    }

}
