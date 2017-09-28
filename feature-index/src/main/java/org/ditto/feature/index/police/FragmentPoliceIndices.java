package org.ditto.feature.index.police;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.base.di.Injectable;
import org.ditto.feature.index.R;
import org.ditto.feature.index.R2;
import org.ditto.feature.index.di.IndexViewModelFactory;
import org.ditto.feature.index.party.PartyIndicesViewModel;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.index.IndexMessage;
import org.ditto.lib.dbroom.index.IndexParty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A fragment representing a listCommandsBy of Items.
 * <p/>
 */
public class FragmentPoliceIndices extends BaseFragment implements Injectable, PoliceIndicesController.AdapterCallbacks {

    @Inject
    IndexViewModelFactory viewModelFactory;

    private PoliceIndicesViewModel viewModel;

    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final PoliceIndicesController controller = new PoliceIndicesController(this, recycledViewPool);

    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentPoliceIndices() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);

        // Many carousels and color models are shown on screen at once. The default recycled view
        // pool size is only 5, so we manually set the pool size to avoid constantly creating new views
        // We also use a shared view pool so that carousels can recycle items between themselves.
        recycledViewPool.setMaxRecycledViews(R.layout.model_color, Integer.MAX_VALUE);
        recycledViewPool.setMaxRecycledViews(R.layout.model_carousel_group, Integer.MAX_VALUE);
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


        return recyclerView;
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PoliceIndicesViewModel.class);
        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, new ArrayList<IndexMessage>());

        viewModel.getLivePartyIndices().observe(this, messages -> {
            datas.put(Constants.DATA1, messages);
            controller.setData((List<IndexParty>) datas.get(Constants.DATA1));
        });
    }


    @Override
    public void onItemUserClicked(IndexParty carousel, int colorPosition) {
        ARouter.getInstance().build("/feature_chat/PersonChatActivity").navigation();
    }

    @Override
    public void onItemUgroupClicked(IndexParty carousel, int colorPosition) {
        Snackbar.make(recyclerView, "TODO: onItemUgroupClicked------", Snackbar.LENGTH_LONG).show();
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


        public FragmentPoliceIndices build() {

            String missing = "";
            if (title == null) {
                missing += " title";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            FragmentPoliceIndices fragment = new FragmentPoliceIndices();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
