package org.ditto.feature.quickfind;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crashlytics.android.Crashlytics;

import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

@Route(path = "/feature_quickfind/QuickfindActivity")
public class QuickfindActivity extends AppCompatActivity   implements QuickfindController.AdapterCallbacks {


    private static final Random RANDOM = new Random();
    private static final String CAROUSEL_DATA_KEY = "carousel_data_key";

    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;
    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final QuickfindController controller = new QuickfindController(this, recycledViewPool);
    private List<QuickfindTipData> carousels = new ArrayList<QuickfindTipData>() {
        {
            for (int i = 0; i < 20; i++)
                add(new QuickfindTipData(i, "关护通测试总监" + i,
                        i + "详细个人咨询业务支持业务深入理解ConstraintLayout之使用姿势约束是一种规则详细个人咨询业务支持业务深入理解ConstraintLayout之使用姿势约束是一种规则,用来表示视图之间的相对关系",
                        i + "咨费￥12/次-3分钟前离你300米内", QuickfindTipData.TYPE.TEXT));
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
        setContentView(R.layout.activity_quickfind);
        ButterKnife.bind(this);



        // Set the adapter

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());


        setupController();

    }

    private void setupController() {
        Timber.d("calling setupController");
        controller.setData(carousels);
        recyclerView.smoothScrollToPosition(carousels.size() - 1);
    }


    @Override
    public void onQuestionContentClicked(QuickfindTipData carousel, int colorPosition) {

    }

    @Override
    public void onBackPressed() {

        this.finish();
    }
}
