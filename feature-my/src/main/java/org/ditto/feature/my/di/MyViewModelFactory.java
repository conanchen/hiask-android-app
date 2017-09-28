package org.ditto.feature.my.di;

import android.arch.lifecycle.ViewModel;

import org.ditto.feature.base.di.BaseViewModelFactory;
import org.ditto.feature.my.myprofile.edit.MiscProfileEditViewModel;
import org.ditto.feature.my.myprofile.edit.ConsultingpriceEditViewModel;
import org.ditto.feature.my.myprofile.edit.ProfessionsEditViewModel;
import org.ditto.feature.my.myprofile.index.MyprofileViewModel;

import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MyViewModelFactory extends BaseViewModelFactory {

    @Inject
    public MyViewModelFactory(final MyViewModelSubComponent viewModelSubComponent) {
        super();
        // we cannot inject view models directly because they won't be bound to the owner's
        // view model scope.

        super.creators.put(MyprofileViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createMyprofileViewModel();
            }
        });

        super.creators.put(ConsultingpriceEditViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createConsultingpriceEditViewModel();
            }
        });


        super.creators.put(ProfessionsEditViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createProfessionsEditViewModel();
            }
        });

        super.creators.put(MiscProfileEditViewModel.class, new Callable<ViewModel>() {
            @Override
            public ViewModel call() throws Exception {
                return viewModelSubComponent.createConsultingdescEditViewModel();
            }
        });
    }

}