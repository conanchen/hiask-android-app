package org.ditto.feature.test;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.lib.apirest.data.GitUser;
import org.ditto.lib.apirest.util.Resource;
import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.user.User;
import org.ditto.lib.dbroom.vo.VoLocation;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TestViewModel extends ViewModel {
    @VisibleForTesting
    final MutableLiveData<String> login = new MutableLiveData<>();
    private final LiveData<Resource<User>> user;

    @Inject
    UsecaseFascade mUsecaseFascade;

    @SuppressWarnings("unchecked")
    @Inject
    public TestViewModel() {
        user = Transformations.switchMap(login, new Function<String, LiveData<Resource<User>>>() {
            @Override
            public LiveData<Resource<User>> apply(String login) {
                if (login == null) {
                    return AbsentLiveData.create();
                } else {
                    return mUsecaseFascade.userUsecase.loadByLogin(login);
                }
            }
        });
    }

    void setLogin(String login) {
        if (Objects.equals(this.login.getValue(), login)) {
            return;
        }
        this.login.setValue(login);

    }

    LiveData<Resource<User>> getUser() {
        return user;
    }

    Observable<GitUser> getRxUser(String login) {
        return mUsecaseFascade.repositoryFascade.apirestFascade.getRxGithubService()
                .getUser(login)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    void retry() {
        if (this.login.getValue() != null) {
            this.login.setValue(this.login.getValue());
        }
    }

    public Observable<Long> updateUserLatestLocation(VoLocation mylocation) {
      return  mUsecaseFascade.userUsecase.updateLatestLocation(mylocation);
    }
}