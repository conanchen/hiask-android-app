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
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * A fragment representing a FragmentEditstepPreview of Items.
 * <p/>
 */
public class FragmentEditstepPreview extends BaseFragment implements ControllerEditstepPreview.AdapterCallbacks {

    @Inject
    BuyanswerViewModelFactory viewModelFactory;
    private BuyanswerEditViewModel viewModel;

    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final ControllerEditstepPreview controller = new ControllerEditstepPreview(this, recycledViewPool);


    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentEditstepPreview() {
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
        datas.put(Constants.DATA1, null);//buyanswer
        datas.put(Constants.DATA2, new ArrayList<BuyanswerContent>());//buyanswerContents
        datas.put(Constants.DATA3, new BuyanswerCommand());//buyanswerCreateCommand
        datas.put(Constants.DATA4, new BuyanswerCommand());//buyanswerSetTime2finishCommand
        datas.put(Constants.DATA5, new BuyanswerCommand());//buyanswerSetGiftCommand
        datas.put(Constants.DATA6, new BuyanswerCommand());//buyanswerSetGeofenceCommand
        datas.put(Constants.DATA7, new ArrayList<BuyanswerCommand>());//buyanswerUpsertContentCommand


        viewModel.getLiveBuyanswer().observe(this, buyanswer -> {
            datas.put(Constants.DATA1, buyanswer);//buyanswer
            controller.setData(datas);
        });
        viewModel.getLiveBuyanswerContents().observe(this,buyanswerContents -> {
            datas.put(Constants.DATA2, buyanswerContents);//buyanswerContents
            controller.setData(datas);
        });
        viewModel.getLiveBuyanswerCreateCommand().observe(this,createCommand->{
            datas.put(Constants.DATA3, createCommand);//buyanswerCreateCommand
            controller.setData(datas);
        });
        viewModel.getLiveSetTime2finishCommand().observe(this,setTime2finishCommand ->{
            datas.put(Constants.DATA4, setTime2finishCommand);//buyanswerSetTime2finishCommand
            controller.setData(datas);
        });
        viewModel.getLiveSetGiftCommand().observe(this,setGiftCommand ->{
            datas.put(Constants.DATA5, setGiftCommand);//buyanswerSetGiftCommand
            controller.setData(datas);
        });
        viewModel.getLiveSetGeofenceCommand().observe(this,setGeofenceCommand->{
            datas.put(Constants.DATA6, setGeofenceCommand);//buyanswerSetGeofenceCommand
            controller.setData(datas);
        });
        viewModel.getLiveUpsertContentCommands().observe(this,upsertContentCommands->{
            datas.put(Constants.DATA7, upsertContentCommands);//buyanswerUpsertContentCommand
            controller.setData(datas);
        });
        viewModel.refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        Context context = recyclerView.getContext();

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

        return recyclerView;
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


    public static Builder builder() {
        return new Builder();
    }

    @Override
    public void onGiftClicked(BuyanswerContent carousel, int colorPosition) {

    }

    public static final class Builder {
        private String title;

        Builder() {
        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }


        public FragmentEditstepPreview build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            FragmentEditstepPreview fragment = new FragmentEditstepPreview();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
