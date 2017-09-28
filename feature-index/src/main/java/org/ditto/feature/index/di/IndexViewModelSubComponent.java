package org.ditto.feature.index.di;


import android.arch.lifecycle.ViewModel;

import org.ditto.feature.index.buyanswer.BuyanswerIndicesViewModel;
import org.ditto.feature.index.doctor.DoctorIndicesViewModel;
import org.ditto.feature.index.friend.FriendIndicesViewModel;
import org.ditto.feature.index.isreal.IsrealIndicesViewModel;
import org.ditto.feature.index.message.MessageIndicesViewModel;
import org.ditto.feature.index.party.PartyIndicesViewModel;
import org.ditto.feature.index.police.PoliceIndicesViewModel;
import org.ditto.feature.index.qfind.QfindIndicesViewModel;
import org.ditto.feature.index.shop.ShopIndicesViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link IndexViewModelFactory}. Using this component allows
 * ViewModels to define {@link javax.inject.Inject} constructors.
 */
@Subcomponent
public interface IndexViewModelSubComponent {

    BuyanswerIndicesViewModel createBuyanswerListViewModel();

    DoctorIndicesViewModel createDoctorIndicesViewModel();

    FriendIndicesViewModel createFriendIndicesViewModel();

    IsrealIndicesViewModel createIsrealIndicesViewModel();

    MessageIndicesViewModel createMessagesViewModel();

    PartyIndicesViewModel createPartyIndicesViewModel();

    PoliceIndicesViewModel createPoliceIndicesViewModel();

    QfindIndicesViewModel createQfindIndicesViewModel();

    ShopIndicesViewModel createShopIndicesViewModel();

    @Subcomponent.Builder
    interface Builder {
        IndexViewModelSubComponent build();
    }
}