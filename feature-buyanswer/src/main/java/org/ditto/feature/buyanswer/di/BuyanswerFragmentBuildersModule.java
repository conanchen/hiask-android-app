package org.ditto.feature.buyanswer.di;


import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepContent;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepGeofence;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepGift;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepPreview;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepTime2finish;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuyanswerFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract FragmentEditstepContent contributeFragmentEditstepContent();

    @ContributesAndroidInjector
    abstract FragmentEditstepTime2finish contributeFragmentEditstepTime();

    @ContributesAndroidInjector
    abstract FragmentEditstepGeofence contributeFragmentEditstepFence();

    @ContributesAndroidInjector
    abstract FragmentEditstepGift contributeFragmentEditstepGift();

    @ContributesAndroidInjector
    abstract FragmentEditstepPreview contributeFragmentEditstepPreview();
}