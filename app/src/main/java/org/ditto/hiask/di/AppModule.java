package org.ditto.hiask.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = TodoViewModelSubComponent.class)
class AppModule {


    @Provides
    @Singleton
    Context providesApplicationContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    TodoViewModelFactory provideCreateArticleViewModelFactory(
            TodoViewModelSubComponent.Builder viewModelSubComponent) {
        return new TodoViewModelFactory(viewModelSubComponent.build());
    }
}