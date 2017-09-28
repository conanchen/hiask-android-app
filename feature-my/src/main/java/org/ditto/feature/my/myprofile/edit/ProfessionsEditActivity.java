package org.ditto.feature.my.myprofile.edit;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.crashlytics.android.Crashlytics;

import org.ditto.feature.base.BaseActivity;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.my.R;
import org.ditto.feature.my.R2;
import org.ditto.feature.my.di.MyViewModelFactory;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.misc.Profession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@Route(path = "/feature_my/ProfessionsEditActivity")
public class ProfessionsEditActivity extends BaseActivity
        implements ProfessionsController.AdapterCallbacks {

    private static final String LOGIN_KEY = "refresh";

    @Inject
    MyViewModelFactory viewModelFactory;

    ProfessionsEditViewModel viewModel;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.tips)
    TextView tipsTextView;

    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final ProfessionsController controller = new ProfessionsController(this, recycledViewPool);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.myprofile_activity_professions_edit);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupRecyclerView();
        setupController();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        viewModel.refresh();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.setAdapter(null);
    }

    @Override
    public void onBackPressed() {
        this.finish();
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfessionsEditViewModel.class);
        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, new ArrayList<Profession>());
        controller.setData((List<Profession>) datas.get(Constants.DATA1));

        viewModel.getLiveProfessions().observe(this, professions -> {
            datas.put(Constants.DATA1, professions);
            controller.setData((List<Profession>) datas.get(Constants.DATA1));
        });
    }

    @Override
    public void onProfessionClicked(Profession carousel, int colorPosition) {
//        viewModel.update(carousel)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        tipsTextView.setText(String.format("设置咨询起价：%d通通币", carousel.price));
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(@NonNull Throwable throwable) throws Exception {
//                        tipsTextView.setText("设置咨询起价：失败");
//                        Timber.e(throwable);
//                    }
//                });

    }
}