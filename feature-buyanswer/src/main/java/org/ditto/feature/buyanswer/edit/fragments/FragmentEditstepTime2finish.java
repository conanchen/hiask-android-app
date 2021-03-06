package org.ditto.feature.buyanswer.edit.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A fragment representing a listCommandsBy of Items.
 * <p/>
 */
public class FragmentEditstepTime2finish extends BaseFragment implements ControllerEditstepTime2finish.AdapterCallbacks {


    @Inject
    BuyanswerViewModelFactory viewModelFactory;
    private BuyanswerEditViewModel viewModel;


    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;

    private static final Random RANDOM = new Random();
    private static final String CAROUSEL_DATA_KEY = "carousel_data_key";
    private static final int SPAN_COUNT = 3;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final ControllerEditstepTime2finish controller = new ControllerEditstepTime2finish(this, recycledViewPool);

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentEditstepTime2finish() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupController();
    }

    private void setupController() {
        Timber.d("calling setupController");
        viewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(BuyanswerEditViewModel.class);

        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, new Buyanswer());
        datas.put(Constants.DATA2, new BuyanswerCommand());
        datas.put(Constants.DATA3, viewModel.getInitTime2finishList());

        controller.setData((Buyanswer) datas.get(Constants.DATA1),
                (BuyanswerCommand) datas.get(Constants.DATA2),
                (List<Long>) datas.get(Constants.DATA3));

        viewModel.getLiveBuyanswer().observe(this, buyanswer -> {
            datas.put(Constants.DATA1, buyanswer);
            controller.setData((Buyanswer) datas.get(Constants.DATA1),
                    (BuyanswerCommand) datas.get(Constants.DATA2),
                    (List<Long>) datas.get(Constants.DATA3));
        });

        viewModel.getLiveSetTime2finishCommand().observe(this, setTime2finishCommand -> {
            datas.put(Constants.DATA2, setTime2finishCommand);
            controller.setData((Buyanswer) datas.get(Constants.DATA1),
                    (BuyanswerCommand) datas.get(Constants.DATA2),
                    (List<Long>) datas.get(Constants.DATA3));
        });
        viewModel.refresh();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }

    @Override
    public void onItemTimeClicked(Long carousel, int position) {
        viewModel.set(BuyanswerCommand.SetTime2finish.builder().setTime2finish(carousel).build());
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

        public FragmentEditstepTime2finish build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            FragmentEditstepTime2finish fragment = new FragmentEditstepTime2finish();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
