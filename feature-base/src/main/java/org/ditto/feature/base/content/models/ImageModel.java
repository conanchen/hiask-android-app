package org.ditto.feature.base.content.models;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

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
public abstract class ImageModel extends EpoxyModelWithHolder<ImageModel.ImageHolder> {
    @EpoxyAttribute @DrawableRes
    int imageRes;
    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    public void bind(ImageHolder holder) {
//        holder.image.setImageResource(imageRes);
        holder.image.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(ImageHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.image.setOnClickListener(null);
//        holder.image.setImageDrawable(null);
    }

   public static class ImageHolder extends EpoxyHolder {
        @BindView(R2.id.image)
        ImageView image;

       View view;

       @Override
       protected void bindView(View itemView) {
           this.view = itemView;
           ButterKnife.bind(this, itemView);
       }

   }
    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        return 1;
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.chat_action_image_model;
    }
}
