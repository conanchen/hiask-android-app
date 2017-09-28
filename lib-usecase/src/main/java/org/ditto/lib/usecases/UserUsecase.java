package org.ditto.lib.usecases;

import android.arch.lifecycle.LiveData;

import com.google.common.base.Strings;

import org.ditto.lib.apirest.util.Resource;
import org.ditto.lib.dbroom.misc.CommandStatus;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.misc.Profession;
import org.ditto.lib.dbroom.user.User;
import org.ditto.lib.dbroom.user.UserCommand;
import org.ditto.lib.dbroom.vo.VoLocation;
import org.ditto.lib.repository.RepositoryFascade;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/6/25.
 */

public class UserUsecase {
    private RepositoryFascade repositoryFascade;

    @Inject
    public UserUsecase(RepositoryFascade repositoryFascade) {
        this.repositoryFascade = repositoryFascade;
    }

    public LiveData<Resource<User>> loadByLogin(String login) {
        return repositoryFascade.userRepository.loadUser(login);
    }

    public List<User> load(int start, int number) {
        return new ArrayList<User>();
    }

    public Observable<Long> updateLatestLocation(VoLocation mylocation) {
        UserCommand command = UserCommand
                .builder()
                .setUuid(UUID.randomUUID().toString())
                .setContent(UserCommand.Content
                        .builder()
                        .setUpdateLatestLocation(
                                UserCommand.UpdateLatestLocation
                                        .builder()
                                        .setLat(mylocation.getLat())
                                        .setLon(mylocation.getLon())
                                        .build())
                        .build())
                .setCommandStatus(CommandStatus.NEW)
                .setCreated(System.currentTimeMillis())
                .build();
        return repositoryFascade.userRepository.save(command).subscribeOn(Schedulers.io());
    }

    public Observable<Long> updateConsultingprice(Gift carousel) {
        UserCommand command = UserCommand
                .builder()
                .setUuid(UUID.randomUUID().toString())
                .setContent(UserCommand.Content
                        .builder()
                        .setUpdateConsultingprice(
                                UserCommand.UpdateConsultingprice
                                        .builder()
                                        .setPrice(carousel.price)
                                        .build())
                        .build())
                .setCommandStatus(CommandStatus.NEW)
                .setCreated(System.currentTimeMillis())
                .build();
        return repositoryFascade.userRepository.save(command).subscribeOn(Schedulers.io());
    }

    public Observable<Long> updateConsultingdesc(String text) {
        UserCommand command = UserCommand
                .builder()
                .setUuid(UUID.randomUUID().toString())
                .setContent(UserCommand.Content
                        .builder()
                        .setUpdateConsultingdesc(
                                UserCommand.UpdateConsultingdesc
                                        .builder()
                                        .setDetail(text)
                                        .build())
                        .build())
                .setCommandStatus(CommandStatus.NEW)
                .setCreated(System.currentTimeMillis())
                .build();
        return repositoryFascade.userRepository.save(command).subscribeOn(Schedulers.io());
    }

    public Observable<Long> updateNickname(String text) {
        UserCommand command = UserCommand
                .builder()
                .setUuid(UUID.randomUUID().toString())
                .setContent(UserCommand.Content
                        .builder()
                        .setUpdateNickname(
                                UserCommand.UpdateNickname
                                        .builder()
                                        .setNickname(text)
                                        .build())
                        .build())
                .setCommandStatus(CommandStatus.NEW)
                .setCreated(System.currentTimeMillis())
                .build();
        return repositoryFascade.userRepository.save(command).subscribeOn(Schedulers.io());
    }

    public Observable<Long> updateProfessions(List<Profession> professions) {
        if (professions == null || professions.size() < 1) {
            throw new AssertionError("processions cannot be empty");
        }

        UserCommand command = UserCommand
                .builder()
                .setUuid(UUID.randomUUID().toString())
                .setContent(UserCommand.Content
                        .builder()
                        .setUpdateProfessions(
                                UserCommand.UpdateProfessions
                                        .builder()
                                        .setProfessions(professions)
                                        .build())
                        .build())
                .setCommandStatus(CommandStatus.NEW)
                .setCreated(System.currentTimeMillis())
                .build();
        return repositoryFascade.userRepository.save(command).subscribeOn(Schedulers.io());
    }
}
