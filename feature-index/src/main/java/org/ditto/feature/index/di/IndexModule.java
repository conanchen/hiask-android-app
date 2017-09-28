package org.ditto.feature.index.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(subcomponents = IndexViewModelSubComponent.class)
public   class IndexModule {

    @Singleton
    @Provides
    IndexViewModelFactory provideMessageViewModelFactory(
            IndexViewModelSubComponent.Builder viewModelSubComponent) {
        return new IndexViewModelFactory(viewModelSubComponent.build());
    }

}