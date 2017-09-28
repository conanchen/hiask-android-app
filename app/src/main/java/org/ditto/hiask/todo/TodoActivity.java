package org.ditto.hiask.todo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.crashlytics.android.Crashlytics;

import org.ditto.feature.base.BaseActivity;
import org.ditto.hiask.R;
import org.ditto.hiask.di.TodoViewModelFactory;
import org.ditto.hiask.todo.model.ArticleTypeData;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

@Route(path = "/app/CreateArticleActivity")
public class TodoActivity extends BaseActivity implements TodoController.AdapterCallbacks {

    private static final String LOGIN_KEY = "refresh";

    @Inject
    TodoViewModelFactory viewModelFactory;

    TodoViewModel viewModel;

    @BindView(R.id.itemlist)
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final TodoController controller = new TodoController(this, recycledViewPool);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.fragment_item_list);
        ButterKnife.bind(this);

        setupRecyclerView();
        setupController();
    }
    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewModel.refresh(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }

    private void setupRecyclerView() {
        // Many carousels and color models are shown on screen at once. The default recycled view
        // pool size is only 5, so we manually set the pool size to avoid constantly creating new views
        // We also use a shared view pool so that carousels can recycle items between themselves.
//        recycledViewPool.setMaxRecycledViews(R.layout.model_color, Integer.MAX_VALUE);
//        recycledViewPool.setMaxRecycledViews(R.layout.model_carousel_group, Integer.MAX_VALUE);
        recyclerView.setRecycledViewPool(recycledViewPool);

        // We are using a multi span grid to allow two columns of buttons. To set this up we need
        // to set our span count on the controller and then get the span size lookup object from
        // the controller. This look up object will delegate span size lookups to each model.
        controller.setSpanCount(SPAN_COUNT);
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());
    }


    private void setupController() {
        Timber.d("calling setupController");
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TodoViewModel.class);
        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, viewModel.getArticleTypeDatas());
        datas.put(Constants.DATA2, new ArrayList<BuyanswerCommand>());
        controller.setData((List<BuyanswerCommand>) datas.get(Constants.DATA2), (List<ArticleTypeData>) datas.get(Constants.DATA1));

        viewModel.getCreateBuyanswerCommands().observe(this, new Observer<List<BuyanswerCommand>>() {
            @Override
            public void onChanged(@Nullable List<BuyanswerCommand> commands) {
                datas.put(Constants.DATA2, commands);
                controller.setData((List<BuyanswerCommand>) datas.get(Constants.DATA2), (List<ArticleTypeData>) datas.get(Constants.DATA1));
                if (gridLayoutManager.getItemCount() > 0) {
                    gridLayoutManager.scrollToPositionWithOffset(gridLayoutManager.getItemCount()-1, 1);
                    recyclerView.smoothScrollToPosition(gridLayoutManager.getItemCount()-1);
                }
            }
        });
    }

    @Override
    public void onArticleBuyanswerClicked(ArticleTypeData carousel, int colorPosition) {
        viewModel.createBuyanswer();
    }

    @Override
    public void onArticleBuyreadClicked(ArticleTypeData carousel, int colorPosition) {
        viewModel.createBuyread();

//        ARouter.getInstance()
//                .build("/feature_article_buyread/BuyreadArticleEditActivity")
//                .navigation();
//        finish();
    }

    @Override
    public void onArticleSellreadClicked(ArticleTypeData carousel, int colorPosition) {
        viewModel.createSellread();
//        ARouter.getInstance()
//                .build("/feature_article_sellread/SellreadArticleEditActivity")
//                .navigation();
//        finish();
    }

    @Override
    public void onCreateCommandBuyanswerClicked(BuyanswerCommand buyanswerCommand, int position) {
        ARouter.getInstance().build("/feature_buyanswer/BuyanswerEditActivity")
                .withString("buyanswerUuid", buyanswerCommand.buyanswerUuid).navigation();
        finish();
    }
}