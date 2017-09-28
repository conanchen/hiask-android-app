package org.ditto.lib.repository;

import android.arch.lifecycle.LiveData;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.util.AppExecutors;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyread.Buyread;
import org.ditto.lib.dbroom.buyanswer.IndexBuyanswer;
import org.ditto.lib.dbroom.misc.Advert;
import org.ditto.lib.dbroom.sellread.Sellread;
import org.ditto.lib.dbroom.ugroup.Ugroup;
import org.ditto.lib.dbroom.user.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Repository that handles VoUser objects.
 */
@Singleton
public class IndexBuyanswerRepository {

    private ApirestFascade githubService;
    private final AppExecutors appExecutors;
    private RoomFascade roomFascade;

    @Inject
    public IndexBuyanswerRepository(ApirestFascade githubService, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.githubService = githubService;
        this.appExecutors = new AppExecutors();
    }

    public LiveData<List<IndexBuyanswer>> listPartyIndicesBy(int size) {

        return roomFascade.daoIndexBuyanswer.listBuyanswerIndicesBy(size);
    }

    public Observable<List<Long>> saveAll() {
        return Observable.fromCallable(
                () -> {
                    List<IndexBuyanswer> indexBuyanswers = new ArrayList<>();
                    long now = System.currentTimeMillis();
                    for (int i = 0; i < 30; ) {
                        indexBuyanswers.add(
                                IndexBuyanswer.builder()
                                        .setUuid("uuid" + i++)
                                        .setType(Buyanswer.class.getSimpleName())
                                        .setTitle(i + "IndexBuyanswer BuyanswerMessage 标题title消灭一切害人虫昵称")
                                        .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                                        .setLastUpdated(now + i)
                                        .build());
                        indexBuyanswers.add(
                                IndexBuyanswer.builder()
                                        .setUuid("uuid" + i++)
                                        .setType(Buyread.class.getSimpleName())
                                        .setTitle(i + "IndexBuyanswer BuyreadMessage 标题title消灭一切害人虫昵称")
                                        .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                                        .setLastUpdated(now + i)
                                        .build());
                        indexBuyanswers.add(
                                IndexBuyanswer.builder()
                                        .setUuid("uuid" + i++)
                                        .setType(Sellread.class.getSimpleName())
                                        .setTitle(i + "IndexBuyanswer SellreadMessage  标题title消灭一切害人虫昵称")
                                        .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                                        .setLastUpdated(now + i)
                                        .build());
                        indexBuyanswers.add(
                                IndexBuyanswer.builder()
                                        .setUuid("uuid" + i++)
                                        .setType(Ugroup.class.getSimpleName())
                                        .setTitle(i + "IndexBuyanswer UgroupMessage 标题title消灭一切害人虫昵称")
                                        .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                                        .setLastUpdated(now + i)
                                        .build());
                        indexBuyanswers.add(
                                IndexBuyanswer.builder()
                                        .setUuid("uuid" + i++)
                                        .setType(User.class.getSimpleName())
                                        .setTitle(i + "IndexBuyanswer UserMessage 标题title消灭一切害人虫昵称")
                                        .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                                        .setLastUpdated(now + i)
                                        .build());
                        indexBuyanswers.add(
                                IndexBuyanswer.builder()
                                        .setUuid("uuid" + i++)
                                        .setType(Advert.class.getSimpleName())
                                        .setTitle(i + "IndexBuyanswer Advert 标题title消灭一切害人虫昵称")
                                        .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                                        .setLastUpdated(now + i)
                                        .build()
                        );
                    }
                    return roomFascade.daoIndexBuyanswer.saveAll(indexBuyanswers);
                }
        ).subscribeOn(Schedulers.io());
    }
}