package org.ditto.feature.base.content.actions;

import android.support.v7.widget.RecyclerView.RecycledViewPool;

import com.airbnb.epoxy.AutoModel;
import com.airbnb.epoxy.TypedEpoxyController;


import org.ditto.feature.base.content.models.ImageAlbumOpenModel_;
import org.ditto.feature.base.content.models.ImageModel_;
import org.ditto.feature.base.content.models.ImagePhotoTakeModel_;
import org.ditto.lib.dbroom.vo.VoImage;

import java.util.List;

public class ActionImagesController extends TypedEpoxyController<List<VoImage>> {
    public interface AdapterCallbacks {

        void onImageClicked(VoImage carousel, int colorPosition);
    }

    @AutoModel
    ImageAlbumOpenModel_ albumOpen;
    @AutoModel
    ImagePhotoTakeModel_ photoTake;

    private final AdapterCallbacks callbacks;
    private final RecycledViewPool recycledViewPool;

    public ActionImagesController(AdapterCallbacks callbacks, RecycledViewPool recycledViewPool) {
        this.callbacks = callbacks;
        this.recycledViewPool = recycledViewPool;
        setDebugLoggingEnabled(true);
    }

    @Override
    protected void buildModels(List<VoImage> carousels) {
        photoTake.addTo(this);
        albumOpen.addTo(this);

        long now = System.currentTimeMillis();
        for (int i = 0; i < carousels.size(); i++) {
            VoImage carousel = carousels.get(i);
            add(new ImageModel_()
                    .id(now + i)
                    .clickListener((model, parentView, clickedView, position) -> {
                        // A model click listener is used instead of a normal click listener so that we can get
                        // the current position of the view. Since the view may have been moved when the colors
                        // were shuffled we can't rely on the position of the model when it was added here to
                        // be correct, since the model won't have been rebound when shuffled.
                        callbacks.onImageClicked(carousel, position);
                    }));

        }
    }

    @Override
    protected void onExceptionSwallowed(RuntimeException exception) {
        // Best practice is to throw in debug so you are aware of any issues that Epoxy notices.
        // Otherwise Epoxy does its best to swallow these exceptions and continue gracefully
        throw exception;
    }
}
