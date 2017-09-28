package org.ditto.feature.my.di;


import org.ditto.feature.my.myprofile.index.MyprofileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract MyprofileFragment contributeMyprofileFragment();
}