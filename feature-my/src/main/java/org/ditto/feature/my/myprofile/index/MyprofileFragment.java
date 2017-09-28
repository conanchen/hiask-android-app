package org.ditto.feature.my.myprofile.index;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.launcher.ARouter;

import org.ditto.feature.base.di.Injectable;
import org.ditto.feature.my.R;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.my.R2;
import org.ditto.feature.my.di.MyViewModelFactory;
import org.ditto.feature.my.myprofile.edit.MiscProfileEditViewModel;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.user.Myprofile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * A fragment representing a listCommandsBy of Items.
 * <p/>
 */
public class MyprofileFragment extends BaseFragment
        implements Injectable, MyprofileController.AdapterCallbacks {

    @Inject
    MyViewModelFactory viewModelFactory;
    private MyprofileViewModel viewModel;
    private MiscProfileEditViewModel miscProfileEditViewModel;


    private static final int SPAN_COUNT = 1;

    // The minimum amount of items to have below your current scroll position before loading more.
    private int visibleLastThreshold = 3 * SPAN_COUNT;
    private int visibleFirstThreshold = 1;
    private boolean loading = false;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final MyprofileController controller = new MyprofileController(this, recycledViewPool);


    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyprofileFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupController();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }

    private void setupController() {
        Timber.d("calling setupController");
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MyprofileViewModel.class);

        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, new ArrayList<Myprofile>());

        viewModel.getLiveProfiles().observe(this, buyanswers -> {
            datas.put(Constants.DATA1, buyanswers);
            controller.setData((ArrayList<Myprofile>) datas.get(Constants.DATA1));
        });
        //---------
        miscProfileEditViewModel = ViewModelProviders.of(this, viewModelFactory).get(MiscProfileEditViewModel.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        // Many myprofileData and color models are shown on screen at once. The default recycled view
        // pool size is only 5, so we manually set the pool size to avoid constantly creating new views
        // We also use a shared view pool so that myprofileData can recycle items between themselves.
//        recycledViewPool.setMaxRecycledViews(R.layout.model_color, Integer.MAX_VALUE);
//        recycledViewPool.setMaxRecycledViews(R.layout.model_carousel_group, Integer.MAX_VALUE);
        recyclerView.setRecycledViewPool(recycledViewPool);

        // We are using a multi span grid to allow two columns of buttons. To set this up we need
        // to set our span count on the controller and then get the span size lookup object from
        // the controller. This look up object will delegate span size lookups to each model.
        controller.setSpanCount(SPAN_COUNT);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!recyclerView.canScrollVertically(1)) {
                        Timber.d("callback.onScrollToBottom() +1,viewModel.loadMore()");
                    }
                    if (!recyclerView.canScrollVertically(-1)) {
                        Timber.d("callback.onScrollToTop() -1,viewModel.refresh()");
                    }
                }
            }
        });

        return view;
    }


    @Override
    public void onColorClicked(Myprofile carousel, int colorPosition) {

    }

    @Override
    public void onConsultingdescClicked(Myprofile carousel, int colorPosition) {
        new MaterialDialog.Builder(this.getActivity())
                .title(R.string.title_update_consultingdesc)
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        miscProfileEditViewModel.updateConsultingdesc(materialDialog.getInputEditText().getText().toString())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        Snackbar.make(recyclerView, "修改成功", Snackbar.LENGTH_LONG).show();
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Snackbar.make(recyclerView, "修改错误", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    }
                })
                .negativeText(R.string.cancel)
                .content("R.string.input_content")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE)
                .inputRangeRes(10, 200, android.R.color.holo_red_dark)
                .input("R.string.input_hint", "R.string.input_prefill", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                    }
                }).show();
    }

    @Override
    public void onNicknameClicked(Myprofile carousel, int colorPosition) {
        new MaterialDialog.Builder(this.getActivity())
                .title(R.string.title_update_nickname)
                .positiveText(R.string.agree)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        miscProfileEditViewModel.updateNickname(materialDialog.getInputEditText().getText().toString())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        Snackbar.make(recyclerView, "修改成功", Snackbar.LENGTH_LONG).show();
                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Snackbar.make(recyclerView, "修改错误", Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    }
                })
                .negativeText(R.string.cancel)
                .content("R.string.input_content")
                .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME)
                .inputRangeRes(10, 200, android.R.color.holo_red_dark)
                .input("R.string.input_hint", "R.string.input_prefill", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                    }
                }).show();
    }

    @Override
    public void onProcessionsClicked(Myprofile myprofile, int position) {
        ARouter.getInstance().build("/feature_my/ProfessionsEditActivity")
                .navigation();

    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;


        Builder() {
        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }


        public MyprofileFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            MyprofileFragment fragment = new MyprofileFragment();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
