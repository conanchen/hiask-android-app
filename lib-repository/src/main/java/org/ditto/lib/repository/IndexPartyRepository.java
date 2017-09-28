package org.ditto.lib.repository;

import android.arch.lifecycle.LiveData;

import org.ditto.lib.apirest.ApirestFascade;
import org.ditto.lib.apirest.util.AppExecutors;
import org.ditto.lib.apirest.GithubService;
import org.ditto.lib.dbroom.RoomFascade;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyread.Buyread;
import org.ditto.lib.dbroom.index.IndexParty;
import org.ditto.lib.dbroom.misc.Advert;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.misc.Profession;
import org.ditto.lib.dbroom.sellread.Sellread;
import org.ditto.lib.dbroom.ugroup.Ugroup;
import org.ditto.lib.dbroom.user.Myprofile;
import org.ditto.lib.dbroom.user.User;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoGift;
import org.ditto.lib.dbroom.vo.VoNamecard;
import org.ditto.lib.dbroom.vo.VoProfession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Repository that handles VoUser objects.
 */
@Singleton
public class IndexPartyRepository {

    private ApirestFascade githubService;
    private final AppExecutors appExecutors;
    private RoomFascade roomFascade;

    @Inject
    public IndexPartyRepository(ApirestFascade githubService, RoomFascade roomFascade) {
        this.roomFascade = roomFascade;
        this.githubService = githubService;
        this.appExecutors = new AppExecutors();
    }

    public LiveData<List<IndexParty>> listPartiesBy(int size) {

        return roomFascade.daoIndexParty.listPartyIndicesBy(size);
    }

    public void saveSamplePartyIndices() {
        long now = System.currentTimeMillis();
        for (int i = 0; i < 30; ) {
            roomFascade.daoIndexParty.saveAll(
                    IndexParty.builder()
                            .setUuid("uuid" + i++)
                            .setType(Buyanswer.class.getSimpleName())
                            .setTitle(i + "IndexParty BuyanswerMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now + i)
                            .build(),
                    IndexParty.builder()
                            .setUuid("uuid" + i++)
                            .setType(Buyread.class.getSimpleName())
                            .setTitle(i + "IndexParty BuyreadMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now + i)
                            .build(),
                    IndexParty.builder()
                            .setUuid("uuid" + i++)
                            .setType(Sellread.class.getSimpleName())
                            .setTitle(i + "IndexParty SellreadMessage  标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now + i)
                            .build(),
                    IndexParty.builder()
                            .setUuid("uuid" + i++)
                            .setType(Ugroup.class.getSimpleName())
                            .setTitle(i + "IndexParty UgroupMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now + i)
                            .build(),
                    IndexParty.builder()
                            .setUuid("uuid" + i++)
                            .setType(User.class.getSimpleName())
                            .setTitle(i + "IndexParty UserMessage 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now + i)
                            .build(),
                    IndexParty.builder()
                            .setUuid("uuid" + i++)
                            .setType(Advert.class.getSimpleName())
                            .setTitle(i + "IndexParty Advert 标题title消灭一切害人虫昵称")
                            .setDetail(i + "详细detail深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系用来表示视图之间的相对关系约束是一种规则,用来表示视图之间的相对关系")
                            .setLastUpdated(now + i)
                            .build()
            );
        }
    }

    public void saveSampleGifts() {
        List<Gift> gifts = new ArrayList<Gift>() {
            {
                int time1 = 1;
                int time2 = 2;
                add(Gift.builder().setUuid("uuid" + time1).setName(time1 + "棒棒糖").setPrice(time1).build());
                add(Gift.builder().setUuid("uuid" + time2).setName(time2 + "棒棒糖").setPrice(time2).build());
                for (int i = 3; i < 22; i++) {
                    int time = time1 + time2;
                    add(Gift.builder().setUuid("uuid" + time).setName(time + "棒棒糖").setPrice(time).build());
                    time1 = time2;
                    time2 = time;
                }
            }
        };
        roomFascade.daoGift.saveAll(gifts);
    }

    public void saveSampleMyprofiles() {
        List<VoProfession> professions = new ArrayList<VoProfession>() {
            {
                add(VoProfession.builder().setName("警察保安").build());
                add(VoProfession.builder().setName("餐饮服务").build());
                add(VoProfession.builder().setName("医务人员").build());
            }
        };
        List<Myprofile> myprofiles = new ArrayList<Myprofile>() {
            {
                int sequence = 1;
                add(Myprofile.builder()
                        .setUuid("uuid" + sequence)
                        .setSequence(sequence++)
                        .setContent(Myprofile.Content
                                .builder()
                                .setBasic(
                                        Myprofile.Basic
                                                .builder()
                                                .setIcon("http://xxx.com/icon/2342342.png")
                                                .setMobilePhone("138 **** 6132")
                                                .setNickName("关护通总府队长")
                                                .setTtNo("838 1234 5678")
                                                .setSex("Unknown")
                                                .build()
                                )
                                .build())
                        .build());


                add(Myprofile.builder()
                        .setUuid("uuid" + sequence)
                        .setSequence(sequence++)
                        .setContent(Myprofile.Content
                                .builder()
                                .setNamecard(VoNamecard
                                        .builder()
                                        .setTitle("移车电话：138 1234 5678")
                                        .setDetail("自己的联系方式、业务介绍，用于签入登记、介绍自己等等")
                                        .build())
                                .build())
                        .build());

                add(Myprofile.builder()
                        .setUuid("uuid" + sequence)
                        .setSequence(sequence++)
                        .setContent(Myprofile.Content
                                .builder()
                                .setVisibleRadius(
                                        Myprofile.VisibleRadius
                                                .builder()
                                                .setRadius(333)
                                                .build()
                                )
                                .build())
                        .build());


                add(Myprofile.builder()
                        .setUuid("uuid" + sequence)
                        .setSequence(sequence++)
                        .setContent(Myprofile.Content
                                .builder()
                                .setVisibleProfessions(professions)
                                .build())
                        .build());


                add(Myprofile.builder()
                        .setUuid("uuid" + sequence)
                        .setSequence(sequence++)
                        .setContent(Myprofile.Content
                                .builder()
                                .setConsultant(Myprofile.Consultant
                                        .builder()
                                        .setPrice(13)
                                        .setDescription("咨询业务摘要咨询业务摘要咨询业务摘要咨询业务摘要咨询业务摘要咨询业务摘要")
                                        .build())
                                .build())
                        .build());

                add(Myprofile.builder()
                        .setUuid("uuid" + sequence)
                        .setSequence(sequence++)
                        .setContent(Myprofile.Content
                                .builder()
                                .setAccounting(Myprofile.Accounting
                                        .builder()
                                        .setBalance(88698)
                                        .setTransactionNum(444)
                                        .build())
                                .build())
                        .build());

            }
        };
        roomFascade.daoUser.saveAll(myprofiles);
    }

    public void saveSampleProfessions() {
        List<Profession> professions = new ArrayList<Profession>() {{
            for (int i = 0; i < 100; i++) {
                add(Profession
                        .builder()
                        .setUuid("uuid" + i)
                        .setSequence(i)
                        .setName("职业" + i)
                        .setDetail("职业描述" + i)
                        .build());
            }
        }};
        roomFascade.daoProfession.saveAll(professions);
    }
}