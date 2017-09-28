package org.ditto.hiask.di;


import org.ditto.feature.test.di.TestViewModelFactory;
import org.ditto.hiask.todo.TodoViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link TestViewModelFactory}. Using this component allows
 * ViewModels to define {@link javax.inject.Inject} constructors.
 */
@Subcomponent
public interface TodoViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        TodoViewModelSubComponent build();
    }
    TodoViewModel createTodoViewModel();
}