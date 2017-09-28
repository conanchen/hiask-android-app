package org.ditto.feature.my.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = MyViewModelSubComponent.class)
public class MyModule {

    @Singleton
    @Provides
    MyViewModelFactory provideMessageViewModelFactory(
            MyViewModelSubComponent.Builder viewModelSubComponent) {
        return new MyViewModelFactory(viewModelSubComponent.build());
    }
}