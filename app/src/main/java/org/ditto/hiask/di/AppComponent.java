package org.ditto.hiask.di;

import android.app.Application;

import org.ditto.feature.buyanswer.di.BuyanswerModule;
import org.ditto.feature.index.di.IndexModule;
import org.ditto.feature.my.di.MyModule;
import org.ditto.feature.test.di.TestModule;
import org.ditto.hiask.HiaskApplication;
import org.ditto.lib.usecases.di.UsecaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,

        AppModule.class,

        MainActivityModule.class,
        TestModule.class,
        MyModule.class,
        IndexModule.class,
        BuyanswerModule.class,

        UsecaseModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(HiaskApplication githubApp);
}