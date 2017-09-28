package org.ditto.feature.index.di;

import android.arch.lifecycle.ViewModel;

import org.ditto.feature.index.buyanswer.BuyanswerIndicesViewModel;
import org.ditto.feature.index.doctor.DoctorIndicesViewModel;
import org.ditto.feature.index.friend.FriendIndicesViewModel;
import org.ditto.feature.index.isreal.IsrealIndicesViewModel;
import org.ditto.feature.index.message.MessageIndicesViewModel;
import org.ditto.feature.base.di.BaseViewModelFactory;
import org.ditto.feature.index.party.PartyIndicesViewModel;
import org.ditto.feature.index.police.PoliceIndicesViewModel;
import org.ditto.feature.index.qfind.QfindIndicesViewModel;
import org.ditto.feature.index.shop.ShopIndicesViewModel;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IndexViewModelFactory extends BaseViewModelFactory {

    @Inject
    public IndexViewModelFactory(final IndexViewModelSubComponent viewModelSubComponent) {
        super();
        // we cannot inject view models directly because they won't be bound to the owner's
        // view model scope.

        super.creators.put(BuyanswerIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createBuyanswerListViewModel();
            }
        });

        super.creators.put(DoctorIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createDoctorIndicesViewModel();
            }
        });

        super.creators.put(FriendIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createFriendIndicesViewModel();
            }
        });


        super.creators.put(IsrealIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createIsrealIndicesViewModel();
            }
        });


        super.creators.put(MessageIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createMessagesViewModel();
            }
        });

        super.creators.put(PartyIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createPartyIndicesViewModel();
            }
        });

        super.creators.put(PoliceIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createPoliceIndicesViewModel();
            }
        });

        super.creators.put(QfindIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createQfindIndicesViewModel();
            }
        });

        super.creators.put(ShopIndicesViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createShopIndicesViewModel();
            }
        });
    }

}