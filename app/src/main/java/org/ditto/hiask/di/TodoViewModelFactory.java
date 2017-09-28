package org.ditto.hiask.di;

import android.arch.lifecycle.ViewModel;

import org.ditto.hiask.todo.TodoViewModel;
import org.ditto.feature.base.di.BaseViewModelFactory;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TodoViewModelFactory extends BaseViewModelFactory {

    @Inject
    public TodoViewModelFactory(final TodoViewModelSubComponent viewModelSubComponent) {
        super();
        // we cannot inject view models directly because they won't be bound to the owner's
        // view model scope.
        super.creators.put(TodoViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createTodoViewModel();
            }
        });
    }

}