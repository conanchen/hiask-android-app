package org.ditto.lib.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.util.ApiResponse;
import org.ditto.lib.apirest.util.AppExecutors;
import org.ditto.lib.apirest.data.GitUser;
import org.ditto.lib.apirest.util.Resource;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.user.Myprofile;
import org.ditto.lib.dbroom.user.User;
import org.ditto.lib.dbroom.user.UserCommand;
import org.ditto.lib.repository.util.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Repository that handles VoUser objects.
 */
@Singleton
public class UserRepository {

    private ApirestFascade apirestFascade;
    private final AppExecutors appExecutors;
    private RoomFascade roomFascade;

    @Inject
    public UserRepository(ApirestFascade apirestFascade, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.apirestFascade = apirestFascade;
        this.appExecutors = new AppExecutors();
    }

    public LiveData<Resource<User>> loadUser(final String login) {
        return new NetworkBoundResource<User, GitUser>(appExecutors) {
            @Override
            protected void saveCallResult(@NonNull GitUser item) {
                item.setCreated(System.currentTimeMillis());
                Timber.e("UserRepository saveCallResult:%s login=%s  " +
                                "\n  reposUrl=%s name=%s " +
                                "\navatarUrl=%s company=%s" +
                                "\n url=%s",
                        item.toString(), item.getLogin(),
                        item.getReposUrl(), item.getName(),
                        item.getAvatarUrl(), item.getCompany(),
                        item.getUrl());

                User user = User.builder().setLogin(item.getLogin()).setName(item.getName()).build();
                roomFascade.daoUser.save(user);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                Timber.e("shouldFetch login=%s, data=%s", login, data);
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                LiveData<User> result = roomFascade.daoUser.load(login);

                Timber.e("loadFromDb login=%s result=%s", login, result);

                return result;
            }

            @Override
            protected void onFetchFailed() {
                Timber.e("onFetchFailed apirestFascade.getVoUser, login=%s", login);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GitUser>> createCall() {
                Timber.e("createCall apirestFascade.getVoUser, login=%s", login);

                return apirestFascade.getGithubService().getUser(login);
            }
        }.asLiveData();


    }

    public LiveData<User> findUserByLogin(String login) {
        return roomFascade.daoUser.load(login);
    }

    public Observable<Long> save(UserCommand command) {
        return Observable.fromCallable(
                () -> roomFascade.daoUser.save(command)
        ).subscribeOn(Schedulers.io());
    }

    public  LiveData<Myprofile> loadProfile(String type,int size) {
        return roomFascade.daoUser.loadProfile(type,size);
    }

    public  LiveData<List<Myprofile>> loadAllProfiles() {
        return roomFascade.daoUser.loadAllProfiles();
    }
}