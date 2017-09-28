package org.ditto.feature.my.myprofile.edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.misc.Profession;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ProfessionsEditViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<String> mutableLogin = new MutableLiveData<>();
    private final LiveData<List<Profession>> liveProfessions;

    @Inject
    UsecaseFascade usecaseFascade;

    @SuppressWarnings("unchecked")
    @Inject
    public ProfessionsEditViewModel() {
        liveProfessions = Transformations.switchMap(mutableLogin, login -> {
            if (login == null) {
                return AbsentLiveData.create();
            } else {
                return usecaseFascade.repositoryFascade.professionRepository.listProfessionsBy(100);
            }
        });
    }


    void retry() {
        if (this.mutableLogin.getValue() != null) {
            this.mutableLogin.setValue(this.mutableLogin.getValue());
        }
    }

    public LiveData<List<Profession>> getLiveProfessions() {
        return this.liveProfessions;
    }

    public void refresh() {
        this.mutableLogin.setValue("GO");
    }

    public Observable<Long> update(List<Profession> carousels) {
        return usecaseFascade.userUsecase
                .updateProfessions(carousels)
                .subscribeOn(Schedulers.io());
    }

}