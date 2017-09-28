package org.ditto.feature.base.content.models;

import android.view.View;
import android.widget.LinearLayout;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import org.ditto.feature.base.R;
import org.ditto.feature.base.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

/**
 * This model shows an example of binding to a specific view type. In this case it is a custom view
 * we made, but it could also be another single view, like an EditText or Button.
 */
@EpoxyModelClass
public abstract class ImageAlbumOpenModel extends EpoxyModelWithHolder<ImageAlbumOpenModel.AlbumOpenHolder> {

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    public void bind(AlbumOpenHolder holder) {
        holder.albumopenLayout.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(AlbumOpenHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.albumopenLayout.setOnClickListener(null);
    }

   public static class AlbumOpenHolder extends EpoxyHolder {
        @BindView(R2.id.albumopen_layout)
        LinearLayout albumopenLayout;

       View view;

       @Override
       protected void bindView(View itemView) {
           this.view = itemView;
           ButterKnife.bind(this, itemView);
       }

   }
    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 2;
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.chat_action_imagealbumopen_model;
    }
}
