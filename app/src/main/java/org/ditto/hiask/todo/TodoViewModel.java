package org.ditto.hiask.todo;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import org.ditto.hiask.todo.model.ArticleTypeData;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.Constants;
import org.ditto.lib.usecases.UsecaseFascade;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class TodoViewModel extends ViewModel {
    @VisibleForTesting
    private final MutableLiveData<Boolean> refresh = new MutableLiveData<>();
    private final LiveData<List<BuyanswerCommand>> createBuyanswerCommands;

    private UsecaseFascade mUsecaseFascade;

    private List<ArticleTypeData> articleTypeDatas = new ArrayList<ArticleTypeData>() {
        {
            add(ArticleTypeData.builder().uuid(Constants.ARTICLE_TYPE_BUYANSWER_UUID).type(ArticleTypeData.TYPE.BUYANSWER).title("悬赏问题 悬赏问题create buy answer article也即是悬赏问题")
                    .detail("detail description create buy answer article也即是悬赏问题类型文章的详细描述,适用于你需要寻求其他人帮助而提问，提问者需要向回答者提供奖赏").build());

            add(ArticleTypeData.builder().uuid(Constants.ARTICLE_TYPE_BUYREAD_UUID).type(ArticleTypeData.TYPE.BUYREAD).title("有赏阅读 有赏阅读create buy read article也即是有赏阅读")
                    .detail("detail description create buy read article也即是有赏阅读类型文章的详细描述，适用于商家发布软文广告而发布的文章，商家需要向阅读者提供奖赏")
                    .build());

            add(ArticleTypeData.builder().uuid(Constants.ARTICLE_TYPE_SELLREAD_UUID).type(ArticleTypeData.TYPE.SELLREAD).title("求赏阅读 求赏阅读create sellread article也即是求赏阅读的文章，适用于你分享知识阅历并希望获取一定赞赏而发布的文章，阅读者可能向作者赞赏")
                    .detail("detail description create buy answer article也即是求赏阅读类型文章的详细描述")
                    .build());

        }
    };

    @SuppressWarnings("unchecked")
    @Inject
    public TodoViewModel(UsecaseFascade mUsecaseFascade) {
        this.mUsecaseFascade = mUsecaseFascade;

        createBuyanswerCommands = Transformations.switchMap(refresh, new Function<Boolean, LiveData<List<BuyanswerCommand>>>() {
            @Override
            public LiveData<List<BuyanswerCommand>> apply(Boolean refresh) {
                return mUsecaseFascade.repositoryFascade.buyanswerRepository
                        .listCommandsBy(BuyanswerCommand.Create.class.getSimpleName(), true, 5);
            }
        });
    }

    void refresh(boolean isRefresh) {
        this.refresh.setValue(isRefresh ? Boolean.TRUE : Boolean.FALSE);

    }

    public LiveData<List<BuyanswerCommand>> getCreateBuyanswerCommands() {
        return createBuyanswerCommands;
    }

    public List<ArticleTypeData> getArticleTypeDatas() {
        return articleTypeDatas;
    }

    public void createBuyanswer() {
        mUsecaseFascade.buyanswerUsecase
                .create()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> refresh(true));
    }

    public void createBuyread() {
        //TODO: BuyreadCommand
    }

    public void createSellread() {
        //TODO: SellreadCommand
    }
}