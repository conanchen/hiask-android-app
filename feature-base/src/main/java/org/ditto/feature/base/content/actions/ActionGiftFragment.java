package org.ditto.feature.base.content.actions;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.R;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.lib.dbroom.misc.Gift;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import timber.log.Timber;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class ActionGiftFragment extends BaseFragment implements ActionGiftsController.AdapterCallbacks {


    private static final Random RANDOM = new Random();
    private static final String CAROUSEL_DATA_KEY = "carousel_data_key";
    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final ActionGiftsController controller = new ActionGiftsController(this, recycledViewPool);
    private List<Gift> carousels = new ArrayList<Gift>() {
        {
            for (int i = 0; i < 20; i++)
                add(Gift.builder().setUuid("uuid" + i).setName("image" + i).setIcon(
                        i + "棒棒糖").setPrice(
                        i + 1).build());
        }
    };

    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActionGiftFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 4);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());


        setupController();


        return recyclerView;
    }

    private void setupController() {
        Timber.d("calling setupController");
        controller.setData(carousels);
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
    public void onGiftClicked(Gift carousel, int colorPosition) {
        Snackbar.make(recyclerView, "VoGift clicked!" + carousel.name, Snackbar.LENGTH_LONG).show();
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


        public ActionGiftFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            ActionGiftFragment fragment = new ActionGiftFragment();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
