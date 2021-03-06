package org.ditto.feature.my.myprofile.edit.epoxymodels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;


import org.ditto.feature.my.R;
import org.ditto.feature.my.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

/**
 * This model shows an example of binding to a specific view type. In this case it is a custom view
 * we made, but it could also be another single view, like an EditText or Button.
 */
@EpoxyModelClass
public abstract class SelectProfessionItemModel extends EpoxyModelWithHolder<SelectProfessionItemModel.Holder> {


    @EpoxyAttribute
    String icon;

    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    String detail;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    public void bind(Holder holder) {
        holder.name.setText(name);
        holder.detail.setText(detail);
        holder.view.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(Holder holder) {
        super.unbind(holder);
    }

    public static class Holder extends EpoxyHolder {
        @BindView(R2.id.icon)
        ImageView icon;
        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.detail)
        TextView detail;


        View view;

        @Override
        protected void bindView(View itemView) {
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        // We want the header to take up all spans so it fills the screen width
        return totalSpanCount;
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.myprofile_select_profession_item_model;
    }
}
