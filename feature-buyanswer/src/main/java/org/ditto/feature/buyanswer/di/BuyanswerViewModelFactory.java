package org.ditto.feature.buyanswer.di;

import android.arch.lifecycle.ViewModel;

import org.ditto.feature.buyanswer.chat.BuyanswerChatViewModel;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;
import org.ditto.feature.base.di.BaseViewModelFactory;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BuyanswerViewModelFactory extends BaseViewModelFactory {

    @Inject
    public BuyanswerViewModelFactory(final BuyanswerViewModelSubComponent viewModelSubComponent) {
        super();
        // we cannot inject view models directly because they won't be bound to the owner's
        // view model scope.
        super.creators.put(BuyanswerChatViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createBuyanswerChatViewModel();
            }
        });
        super.creators.put(BuyanswerEditViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createBuyanswerEditViewModel();
            }
        });
    }

}