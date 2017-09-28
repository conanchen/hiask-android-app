package org.ditto.feature.my.myprofile.edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.user.Myprofile;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MiscProfileEditViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<String> mutableLogin = new MutableLiveData<>();
    private final LiveData<Myprofile> liveConsultant;

    @Inject
    UsecaseFascade usecaseFascade;

    @SuppressWarnings("unchecked")
    @Inject
    public MiscProfileEditViewModel() {
        liveConsultant = Transformations.switchMap(mutableLogin, login -> {
            if (login == null) {
                return AbsentLiveData.create();
            } else {
                return usecaseFascade.repositoryFascade.userRepository
                        .loadProfile(Myprofile.Consultant.class.getSimpleName(),1);
            }
        });
    }


    void retry() {
        if (this.mutableLogin.getValue() != null) {
            this.mutableLogin.setValue(this.mutableLogin.getValue());
        }
    }

    public LiveData<Myprofile> getLiveConsultant() {
        return liveConsultant;
    }

    public void refresh() {
        this.mutableLogin.setValue("GO");
    }

    public Observable<Long> updateConsultingdesc(String text) {
        return usecaseFascade.userUsecase
                .updateConsultingdesc(text)
                .subscribeOn(Schedulers.io());
    }
    public Observable<Long> updateNickname(String text) {
        return usecaseFascade.userUsecase
                .updateNickname(text)
                .subscribeOn(Schedulers.io());
    }
}