package org.ditto.feature.my.myprofile.edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class ConsultingpriceEditViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<String> mutableLogin = new MutableLiveData<>();
    private final LiveData<List<Gift>> liveGifts;

    @Inject
    UsecaseFascade usecaseFascade;

    @SuppressWarnings("unchecked")
    @Inject
    public ConsultingpriceEditViewModel() {
        liveGifts = Transformations.switchMap(mutableLogin, login -> {
            if (login == null) {
                return AbsentLiveData.create();
            } else {
                return usecaseFascade.repositoryFascade.giftRepository.listGiftsBy(100);
            }
        });
    }


    void retry() {
        if (this.mutableLogin.getValue() != null) {
            this.mutableLogin.setValue(this.mutableLogin.getValue());
        }
    }

    public LiveData<List<Gift>> getLiveGifts() {
        return this.liveGifts;
    }

    public void refresh() {
        this.mutableLogin.setValue("GO");
    }

    public Observable<Long> update(Gift carousel) {
        return usecaseFascade.userUsecase
                .updateConsultingprice(carousel)
                .subscribeOn(Schedulers.io());
    }
}