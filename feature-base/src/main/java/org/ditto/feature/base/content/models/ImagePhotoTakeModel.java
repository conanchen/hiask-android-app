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
public abstract class ImagePhotoTakeModel extends EpoxyModelWithHolder<ImagePhotoTakeModel.PhotoTakeHolder> {

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    public void bind(PhotoTakeHolder holder) {
        holder.phototakeLayout.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(PhotoTakeHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.phototakeLayout.setOnClickListener(null);
    }

    public static class PhotoTakeHolder extends EpoxyHolder {
        @BindView(R2.id.phototake_layout)
        LinearLayout phototakeLayout;

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
        return R.layout.chat_action_imagephototake_model;
    }
}
