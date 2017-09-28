package org.ditto.feature.buyanswer.di;


import org.ditto.feature.buyanswer.chat.BuyanswerChatViewModel;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link BuyanswerViewModelFactory}. Using this component allows
 * ViewModels to define {@link javax.inject.Inject} constructors.
 */
@Subcomponent
public interface BuyanswerViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        BuyanswerViewModelSubComponent build();
    }
    BuyanswerEditViewModel createBuyanswerEditViewModel();
    BuyanswerChatViewModel createBuyanswerChatViewModel();
}