package org.ditto.feature.my.di;


import android.arch.lifecycle.ViewModel;

import org.ditto.feature.my.myprofile.edit.MiscProfileEditViewModel;
import org.ditto.feature.my.myprofile.edit.ConsultingpriceEditViewModel;
import org.ditto.feature.my.myprofile.edit.ProfessionsEditViewModel;
import org.ditto.feature.my.myprofile.index.MyprofileViewModel;

import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 * {@link MyViewModelFactory}. Using this component allows
 * ViewModels to define {@link javax.inject.Inject} constructors.
 */
@Subcomponent
public interface MyViewModelSubComponent {
    ConsultingpriceEditViewModel createConsultingpriceEditViewModel();
    MyprofileViewModel createMyprofileViewModel();

    MiscProfileEditViewModel createConsultingdescEditViewModel();

    ProfessionsEditViewModel createProfessionsEditViewModel();

    @Subcomponent.Builder
    interface Builder {
        MyViewModelSubComponent build();
    }

}