package org.ditto.feature.base.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.v4.util.ArrayMap;


import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Singleton;

@Singleton
public class BaseViewModelFactory implements ViewModelProvider.Factory {
    protected  final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    public BaseViewModelFactory( ) {
        creators = new ArrayMap<>();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}