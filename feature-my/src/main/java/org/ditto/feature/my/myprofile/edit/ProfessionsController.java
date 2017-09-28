package org.ditto.feature.my.myprofile.edit;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.TypedEpoxyController;

import org.ditto.feature.my.myprofile.edit.epoxymodels.SelectProfessionItemModel_;
import org.ditto.lib.dbroom.misc.Profession;
import org.ditto.lib.dbroom.misc.Profession;

import java.util.List;


public class ProfessionsController extends TypedEpoxyController<List<Profession>> {
    public interface AdapterCallbacks {
        void onProfessionClicked(Profession carousel, int colorPosition) ;
    }


    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public ProfessionsController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<Profession> carousels) {
        if (carousels != null) {
            for (Profession carousel:carousels) {
                add(new SelectProfessionItemModel_()
                        .id(carousel.uuid)
                        .name(carousel.name)
                        .clickListener((model, parentView, clickedView, position) -> {
                            // A model click listener is used instead of a normal click listener so that we can get
                            // the current position of the view. Since the view may have been moved when the colors
                            // were shuffled we can't rely on the position of the model when it was added here to
                            // be correct, since the model won't have been rebound when shuffled.
                            callbacks.onProfessionClicked(carousel, position);
                        }));

            }
        }
    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
