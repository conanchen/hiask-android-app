package org.ditto.feature.my.myprofile.index;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.buyanswer.IndexBuyanswer;
import org.ditto.lib.dbroom.user.Myprofile;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;

import javax.inject.Inject;

public class MyprofileViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<String> mutableLogin = new MutableLiveData<>();
    private final LiveData<List<Myprofile>> liveProfiles;

    @Inject
    UsecaseFascade usecaseFascade;

    @SuppressWarnings("unchecked")
    @Inject
    public MyprofileViewModel() {
        liveProfiles = Transformations.switchMap(mutableLogin, login -> {
            if (login == null) {
                return AbsentLiveData.create();
            } else {
                return usecaseFascade.repositoryFascade.userRepository.loadAllProfiles();
            }
        });
    }


    void retry() {
        if (this.mutableLogin.getValue() != null) {
            this.mutableLogin.setValue(this.mutableLogin.getValue());
        }
    }

    public LiveData<List<Myprofile>> getLiveProfiles() {
        return this.liveProfiles;
    }

    public void refresh() {
        this.mutableLogin.setValue("GO");
    }
}