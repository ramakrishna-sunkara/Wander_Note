package com.wonder.wandernote.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import com.wonder.wandernote.WanderMvvmApp;
import com.wonder.wandernote.di.builder.ActivityBuilder;
import com.wonder.wandernote.di.module.AppModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(WanderMvvmApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
