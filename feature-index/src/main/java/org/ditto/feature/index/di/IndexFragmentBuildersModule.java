package org.ditto.feature.index.di;


import org.ditto.feature.index.buyanswer.FragmentBuyanswerIndices;
import org.ditto.feature.index.doctor.FragmentDoctorIndices;
import org.ditto.feature.index.friend.FragmentFriendIndices;
import org.ditto.feature.index.isreal.FragmentIsrealIndices;
import org.ditto.feature.index.message.FragmentMessageIndices;
import org.ditto.feature.index.party.FragmentPartyIndices;
import org.ditto.feature.index.police.FragmentPoliceIndices;
import org.ditto.feature.index.qfind.FragmentQfindIndices;
import org.ditto.feature.index.shop.FragmentShopIndices;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class IndexFragmentBuildersModule {
    @ContributesAndroidInjector
    abstract FragmentBuyanswerIndices contributeFragmentBuyanswerIndices();

    @ContributesAndroidInjector
    abstract FragmentDoctorIndices contributeFragmentDoctorIndices();

    @ContributesAndroidInjector
    abstract FragmentFriendIndices contributeFragmentFriendIndices();


    @ContributesAndroidInjector
    abstract FragmentIsrealIndices contributeFragmentIsrealIndices();

    @ContributesAndroidInjector
    abstract FragmentMessageIndices contributeFragmentMessageIndices();

    @ContributesAndroidInjector
    abstract FragmentPartyIndices contributeFragmentPartyIndices();

    @ContributesAndroidInjector
    abstract FragmentPoliceIndices contributeFragmentPoliceIndices();

    @ContributesAndroidInjector
    abstract FragmentQfindIndices contributeFragmentQfindIndices();

    @ContributesAndroidInjector
    abstract FragmentShopIndices contributeFragmentShopIndices();
}