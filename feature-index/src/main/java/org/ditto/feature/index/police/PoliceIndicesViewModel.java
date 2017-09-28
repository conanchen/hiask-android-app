package org.ditto.feature.index.police;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.index.IndexParty;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;

import javax.inject.Inject;

public class PoliceIndicesViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<String> mutableLogin = new MutableLiveData<>();
    private final LiveData<List<IndexParty>> livePartyIndices;

    @Inject
    UsecaseFascade usecaseFascade;

    @SuppressWarnings("unchecked")
    @Inject
    public PoliceIndicesViewModel() {
        livePartyIndices = Transformations.switchMap(mutableLogin, login -> {
            if (login == null) {
                return AbsentLiveData.create();
            } else {
                return usecaseFascade.repositoryFascade.indexPartyRepository.listPartiesBy(100);
            }
        });
    }


    void retry() {
        if (this.mutableLogin.getValue() != null) {
            this.mutableLogin.setValue(this.mutableLogin.getValue());
        }
    }

    public LiveData<List<IndexParty>> getLivePartyIndices() {
        return this.livePartyIndices;
    }

    public void refresh() {
        this.mutableLogin.setValue("GO");
    }
}