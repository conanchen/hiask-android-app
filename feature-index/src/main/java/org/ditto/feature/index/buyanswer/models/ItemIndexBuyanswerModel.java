package org.ditto.feature.index.buyanswer.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;


import org.ditto.feature.index.R;
import org.ditto.feature.index.R2;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

/**
 * This model shows an example of binding to a specific view type. In this case it is a custom view
 * we made, but it could also be another single view, like an EditText or Button.
 */
@EpoxyModelClass
public abstract class ItemIndexBuyanswerModel extends EpoxyModelWithHolder<ItemIndexBuyanswerModel.IssueHolder> {
    @EpoxyAttribute
    String questioner;
    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    String detaill;
    @EpoxyAttribute
    String require;


    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;


    @Override
    public void bind(IssueHolder holder) {
        holder.title.setText(title);
        holder.detail.setText(detaill);
        holder.view.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(IssueHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.view.setOnClickListener(null);
    }


    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        // We want the header to take up all spans so it fills the screen width
        return totalSpanCount;
    }


    public static class IssueHolder extends EpoxyHolder {

        @BindView(R2.id.issue_title) TextView title;
        @BindView(R2.id.issue_detail) TextView detail;
        @BindView(R2.id.issue_require) TextView require;


        View view;

        @Override
        protected void bindView(View itemView) {
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    protected int getDefaultLayout() {
        return R.layout.buyanswer_item_model;
    }

}

