package org.ditto.feature.buyanswer.edit;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.google.common.base.Strings;

import org.ditto.lib.apirest.util.AbsentLiveData;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.vo.VoAudio;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;
import org.ditto.lib.dbroom.vo.VoText;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class BuyanswerEditViewModel extends ViewModel {
    @Inject
    UsecaseFascade mUsecaseFascade;

    @VisibleForTesting
    final MutableLiveData<String> mMutableLiveBuyanswerId = new MutableLiveData<>();


    private LiveData<Buyanswer> liveBuyanswer;
    private LiveData<List<BuyanswerCommand>> liveUpsertContentCommands;
    private LiveData<BuyanswerCommand> liveSetTime2finishCommand;
    private LiveData<BuyanswerCommand> liveUpateTitleCommand;
    private LiveData<BuyanswerCommand> liveSetGiftCommand;
    private LiveData<BuyanswerCommand> liveSetGeofenceCommand;
    private LiveData<List<BuyanswerContent>> liveBuyanswerContents;
    private LiveData<BuyanswerCommand> liveCreateBuyanswerCommand;

    @SuppressWarnings("unchecked")
    @Inject
    public BuyanswerEditViewModel() {
        liveBuyanswer = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository.loadLive(buyanswerId);
            }
        });
        liveBuyanswerContents = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .listContentsBy(buyanswerId, 100);
            }
        });
        liveCreateBuyanswerCommand = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .findLatestCommandBy(buyanswerId, BuyanswerCommand.Create.class.getSimpleName());
            }
        });

        liveUpateTitleCommand = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .findLatestCommandBy(buyanswerId, BuyanswerCommand.UpdateTitle.class.getSimpleName());
            }
        });

        liveUpsertContentCommands = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .listCommandsBy(buyanswerId, BuyanswerCommand.UpsertContent.class.getSimpleName(), true, 100);
            }
        });
        liveSetTime2finishCommand = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .findLatestCommandBy(buyanswerId, BuyanswerCommand.SetTime2finish.class.getSimpleName());
            }
        });
        liveSetGiftCommand = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .findLatestCommandBy(buyanswerId, BuyanswerCommand.SetGift.class.getSimpleName());
            }
        });
        liveSetGeofenceCommand = Transformations.switchMap(mMutableLiveBuyanswerId, (buyanswerId) -> {
            if (Strings.isNullOrEmpty(buyanswerId)) {
                return AbsentLiveData.create();
            } else {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .findLatestCommandBy(buyanswerId, BuyanswerCommand.SetGeofence.class.getSimpleName());
            }
        });
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

    public LiveData<Buyanswer> getLiveBuyanswer() {
        return liveBuyanswer;
    }

    public LiveData<BuyanswerCommand> getLiveUpdateTitleCommand() {
        return liveUpateTitleCommand;
    }

    public LiveData<List<BuyanswerCommand>> getLiveUpsertContentCommands() {
        return liveUpsertContentCommands;
    }

    public LiveData<BuyanswerCommand> getLiveSetTime2finishCommand() {
        return liveSetTime2finishCommand;
    }

    public LiveData<BuyanswerCommand> getLiveSetGiftCommand() {
        return liveSetGiftCommand;
    }

    public LiveData<BuyanswerCommand> getLiveSetGeofenceCommand() {
        return liveSetGeofenceCommand;
    }

    public List<Long> getInitTime2finishList() {
        return mUsecaseFascade.buyanswerUsecase.getInitTime2finishList();
    }

    public List<VoGift> getInitGiftList() {
        return mUsecaseFascade.buyanswerUsecase.getInitGiftList();
    }

    public LiveData<List<BuyanswerContent>> getLiveBuyanswerContents() {
        return liveBuyanswerContents;
    }

    public LiveData<BuyanswerCommand> getLiveBuyanswerCreateCommand() {
        return liveCreateBuyanswerCommand;
    }

    public void upsertContent(VoText voText) {
        if (mMutableLiveBuyanswerId != null && !Strings.isNullOrEmpty(mMutableLiveBuyanswerId.getValue())) {
            mUsecaseFascade.buyanswerUsecase
                    .upsertContent(UUID.randomUUID().toString(),
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

    public void set(BuyanswerCommand.SetTime2finish setTime2finish) {
        if (mMutableLiveBuyanswerId != null && !Strings.isNullOrEmpty(mMutableLiveBuyanswerId.getValue())) {
            mUsecaseFascade.buyanswerUsecase
                    .set(UUID.randomUUID().toString(), mMutableLiveBuyanswerId.getValue(), setTime2finish)
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                @Override
                public void accept(@NonNull Long aLong) throws Exception {
                    refresh();
                }
            });

        }
    }

    public void set(VoGeofence voGeofence) {
        if (mMutableLiveBuyanswerId != null && !Strings.isNullOrEmpty(mMutableLiveBuyanswerId.getValue())) {
            mUsecaseFascade.buyanswerUsecase
                    .set(UUID.randomUUID().toString(), mMutableLiveBuyanswerId.getValue(), voGeofence)
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                @Override
                public void accept(@NonNull Long aLong) throws Exception {
                    refresh();
                }
            });

        }
    }

    public void set(VoGift voGift) {
        if (mMutableLiveBuyanswerId != null && !Strings.isNullOrEmpty(mMutableLiveBuyanswerId.getValue())) {
            mUsecaseFascade.buyanswerUsecase
                    .set(UUID.randomUUID().toString(), mMutableLiveBuyanswerId.getValue(), voGift)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            refresh();
                        }
                    });

        }
    }

    public Observable<Long> publish() {
        return mUsecaseFascade.buyanswerUsecase
                .publish(UUID.randomUUID().toString(), mMutableLiveBuyanswerId.getValue())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void updateTitle(String title) {
        if (!Strings.isNullOrEmpty(title)) {
            mUsecaseFascade.buyanswerUsecase
                    .updateTitle(UUID.randomUUID().toString(), mMutableLiveBuyanswerId.getValue(), title)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(@NonNull Long aLong) throws Exception {
                            refresh();
                        }
                    });
        }
    }

}