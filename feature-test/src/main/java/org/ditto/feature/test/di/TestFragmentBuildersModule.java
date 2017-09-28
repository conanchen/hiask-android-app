package org.ditto.feature.test.di;


import org.ditto.feature.test.TestFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class TestFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TestFragment contributeTestFragment();

}