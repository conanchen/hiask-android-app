package org.ditto.feature.buyanswer.chat;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.google.common.base.Strings;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerMessage;
import org.ditto.lib.dbroom.vo.VoText;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class BuyanswerChatViewModel extends ViewModel {

    @Inject
    UsecaseFascade mUsecaseFascade;

    @VisibleForTesting
    final MutableLiveData<String> mMutableLiveBuyanswerId = new MutableLiveData<>();

    private final LiveData<List<BuyanswerMessage>> liveBuyanswerMessages;
    private final LiveData<List<BuyanswerCommand>> liveUpsertMessageCommands;

    @SuppressWarnings("unchecked")
    @Inject
    public BuyanswerChatViewModel() {
        liveBuyanswerMessages = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository.ListMessagesBy(buyanswerId, 100);
            }
        });
        liveUpsertMessageCommands = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade
                        .buyanswerRepository
                        .listCommandsBy(BuyanswerCommand.UpsertMessage.class.getSimpleName(), true, 100);
            }
        });
    }

    LiveData<List<BuyanswerMessage>> getLiveBuyanswerMessages() {
        return liveBuyanswerMessages;
    }

    public void refresh(String buyanswerid) {
        if (!Strings.isNullOrEmpty(buyanswerid)) {
            this.mMutableLiveBuyanswerId.setValue(buyanswerid);
        }
    }

    public void refresh() {
        if (!Strings.isNullOrEmpty(mMutableLiveBuyanswerId.getValue())) {
            this.mMutableLiveBuyanswerId.setValue(mMutableLiveBuyanswerId.getValue());
        }
    }

    public LiveData<List<BuyanswerCommand>> getLiveUpsertMessageCommands() {
        return liveUpsertMessageCommands;
    }


    public void upsertMessage(VoText voText) {
        if (mMutableLiveBuyanswerId != null && !Strings.isNullOrEmpty(mMutableLiveBuyanswerId.getValue())) {
            mUsecaseFascade.buyanswerUsecase
                    .upsertMessage(UUID.randomUUID().toString(),
                            mMutableLiveBuyanswerId.getValue(),
                            UUID.randomUUID().toString(),
                            voText)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            refresh();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable throwable) throws Exception {
                            Timber.e(throwable);
                        }
                    });
        }
    }

}