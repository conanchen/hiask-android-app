package org.ditto.lib.repository;

import android.arch.lifecycle.LiveData;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.util.AppExecutors;
import org.ditto.lib.apirest.GithubService;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyread.Buyread;
import org.ditto.lib.dbroom.index.IndexMessage;
import org.ditto.lib.dbroom.misc.Advert;
import org.ditto.lib.dbroom.sellread.Sellread;
import org.ditto.lib.dbroom.ugroup.Ugroup;
import org.ditto.lib.dbroom.user.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Repository that handles VoUser objects.
 */
@Singleton
public class IndexMessageRepository {

    private ApirestFascade githubService;
    private final AppExecutors appExecutors;
    private RoomFascade roomFascade;

    @Inject
    public IndexMessageRepository(ApirestFascade githubService, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.githubService = githubService;
        this.appExecutors = new AppExecutors();
    }


    public LiveData<List<IndexMessage>> listMessagesBy(int size) {

        return roomFascade.daoIndexMessage.listMessageIndicesBy(size);
    }


    public void saveSampleMessageIndices() {
        long now = System.currentTimeMillis();
        for (int i = 0; i < 30; ) {
            roomFascade.daoIndexMessage.saveAll(
                    IndexMessage.builder()
                            .setUuid("uuid" + i++)
                            .setType(Buyanswer.class.getSimpleName())
                            .setTitle(i + "BuyanswerMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now+i)
                            .build(),
                    IndexMessage.builder()
                            .setUuid("uuid" + i++)
                            .setType(Buyread.class.getSimpleName())
                            .setTitle(i + "BuyreadMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now+i)
                            .build(),
                    IndexMessage.builder()
                            .setUuid("uuid" + i++)
                            .setType(Sellread.class.getSimpleName())
                            .setTitle(i + "SellreadMessage  标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now+i)
                            .build(),
                    IndexMessage.builder()
                            .setUuid("uuid" + i++)
                            .setType(Ugroup.class.getSimpleName())
                            .setTitle(i + "UgroupMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now+i)
                            .build(),
                    IndexMessage.builder()
                            .setUuid("uuid" + i++)
                            .setType(User.class.getSimpleName())
                            .setTitle(i + "UserMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now+i)
                            .build(),
                    IndexMessage.builder()
                            .setUuid("uuid" + i++)
                            .setType(Advert.class.getSimpleName())
                            .setTitle(i + "Advert 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now+i)
                            .build()
            );
        }
    }
}