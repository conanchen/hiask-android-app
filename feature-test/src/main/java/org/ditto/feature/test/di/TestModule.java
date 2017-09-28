package org.ditto.feature.test.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = TestViewModelSubComponent.class)
public class TestModule {

    @Singleton
    @Provides
    TestViewModelFactory provideTestViewModelFactory(
            TestViewModelSubComponent.Builder viewModelSubComponent) {
        return new TestViewModelFactory(viewModelSubComponent.build());
    }


}