package org.ditto.feature.my.myprofile.index;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.common.base.Strings;

import org.ditto.feature.my.myprofile.index.models.AccountModel_;
import org.ditto.feature.my.myprofile.index.models.AccountbookModel_;
import org.ditto.feature.my.myprofile.index.models.ConsultingdescModel_;
import org.ditto.feature.my.myprofile.index.models.ConsultingpriceModel_;
import org.ditto.feature.my.myprofile.index.models.LogoutModel_;
import org.ditto.feature.my.myprofile.index.models.MobileModel_;
import org.ditto.feature.my.myprofile.index.models.NamecardModel_;
import org.ditto.feature.my.myprofile.index.models.ProfessionsModel_;
import org.ditto.feature.my.myprofile.index.models.PurseModel_;
import org.ditto.feature.my.myprofile.index.models.ReachableModel_;
import org.ditto.lib.dbroom.user.Myprofile;
import org.ditto.lib.dbroom.vo.VoNamecard;
import org.ditto.lib.dbroom.vo.VoProfession;

import java.util.List;

import timber.log.Timber;


public class MyprofileController extends TypedEpoxyController<List<Myprofile>> {
    public interface AdapterCallbacks {

        void onColorClicked(Myprofile carousel, int colorPosition);

        void onConsultingdescClicked(Myprofile carousel, int colorPosition);

        void onNicknameClicked(Myprofile carousel, int colorPosition);

        void onProcessionsClicked(Myprofile myprofile, int position);
    }


    @AutoModel
    LogoutModel_ logoutModel_;

    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public MyprofileController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<Myprofile> carousels) {
        if (carousels != null) {
            for (Myprofile myprofile : carousels) {
                if (Myprofile.Basic.class.getSimpleName().equals(myprofile.type)) {
                    add(new AccountModel_()
                            .id(myprofile.uuid)
                            .name(myprofile.content.basic.nickName)
                            .accountNo("通通号：" + myprofile.content.basic.ttNo)
                            .nickNameClickListener((model, holder, view, position) -> {
                                callbacks.onNicknameClicked(myprofile, position);
                            })
                    );
                    add(new MobileModel_()
                            .id(myprofile.uuid + "1")
                            .phoneNo(myprofile.content.basic.mobilePhone)
                    );
                } else if (VoNamecard.class.getSimpleName().equals(myprofile.type)) {
                    add(new NamecardModel_()
                            .id(myprofile.uuid)
                            .title(myprofile.content.namecard.title)
                            .detail(myprofile.content.namecard.detail)
                    );
                } else if (Myprofile.VisibleRadius.class.getSimpleName().equals(myprofile.type)) {
                    add(new ReachableModel_()
                            .id(myprofile.uuid)
                            .detail(String.format("以当前位置为中心，可以在%d米半径内找到我", myprofile.content.visibleRadius.radius))
                    );
                } else if (VoProfession.class.getSimpleName().equals(myprofile.type)) {
                    String pnames = "";
                    for (VoProfession profession : myprofile.content.visibleProfessions) {
                        if (Strings.isNullOrEmpty(pnames)) {
                            pnames = profession.name;
                        } else {
                            pnames += "、" + profession.name;
                        }
                    }
                    add(new ProfessionsModel_()
                            .id(myprofile.uuid)
                            .detail(String.format("%s，可以在这些职业频道找到我", pnames))
                            .clickListener((model, holder, view, position) -> {
                                callbacks.onProcessionsClicked(myprofile, position);
                            })
                    );
                } else if (Myprofile.Consultant.class.getSimpleName().equals(myprofile.type)) {
                    add(new ConsultingpriceModel_()
                            .id(myprofile.uuid)
                            .feeValue(myprofile.content.consultant.price)
                            .clickListener((model, holder, view, position) -> {
                                ARouter.getInstance().build("/feature_my/ConsultingpriceEditActivity")
                                        .navigation();
                            }));
                    add(new ConsultingdescModel_()
                            .id(myprofile.uuid + "1")
                            .desc(myprofile.content.consultant.description)
                            .clickListener((model, holder, view, position) -> {
                                callbacks.onConsultingdescClicked(myprofile, position);
                            }));
                } else if (Myprofile.Accounting.class.getSimpleName().equals(myprofile.type)) {
                    add(new PurseModel_()
                            .id(myprofile.uuid)
                            .balance(myprofile.content.accounting.balance));
                    add(new AccountbookModel_()
                            .id(myprofile.uuid + "1")
                            .transactionCount(myprofile.content.accounting.transactionNum));
                } else {
                    Timber.e("TODO:// not handle yet: myprofile.type=%s", myprofile.type);
                }
            }
        }


        logoutModel_.clickListener((model, holder, view, position) -> {
            ARouter.getInstance().build("/feature_login/LoginActivity")
                    .navigation();
        }).addTo(this);

    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
