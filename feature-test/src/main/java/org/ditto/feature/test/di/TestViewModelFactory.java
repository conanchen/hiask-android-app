package org.ditto.feature.test.di;

import android.arch.lifecycle.ViewModel;


import org.ditto.feature.test.TestViewModel;
import org.ditto.feature.base.di.BaseViewModelFactory;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestViewModelFactory extends BaseViewModelFactory {

    @Inject
    public TestViewModelFactory(final TestViewModelSubComponent viewModelSubComponent) {
        super();
        // we cannot inject view models directly because they won't be bound to the owner's
        // view model scope.
        super.creators.put(TestViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.testViewModel();
            }
        });
    }

}