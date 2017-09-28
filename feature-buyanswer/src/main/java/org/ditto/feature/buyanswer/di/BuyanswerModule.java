package org.ditto.feature.buyanswer.di;

import org.ditto.feature.buyanswer.chat.BuyanswerChatActivity;
import org.ditto.feature.buyanswer.edit.BuyanswerEditActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module(subcomponents = BuyanswerViewModelSubComponent.class)
public   class BuyanswerModule {

    @Singleton
    @Provides
    BuyanswerViewModelFactory provideBuyanswerViewModelFactory(
            BuyanswerViewModelSubComponent.Builder viewModelSubComponent) {
        return new BuyanswerViewModelFactory(viewModelSubComponent.build());
    }

}