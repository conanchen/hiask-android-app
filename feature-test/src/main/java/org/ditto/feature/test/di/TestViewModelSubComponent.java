package org.ditto.feature.test.di;


import org.ditto.feature.test.TestViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link TestViewModelFactory}. Using this component allows
 * ViewModels to define {@link javax.inject.Inject} constructors.
 */
@Subcomponent
public interface TestViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        TestViewModelSubComponent build();
    }
    TestViewModel testViewModel();
}