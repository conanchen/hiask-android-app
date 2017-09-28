package org.ditto.feature.index.isreal.epoxymodels;

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
public abstract class ItemIndexMessageModel extends EpoxyModelWithHolder<ItemIndexMessageModel.MessageIssueHolder> {
    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    String detaill;
    @EpoxyAttribute
    String require;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;


    @Override
    protected int getDefaultLayout() {
        return R.layout.message_item_model;
    }

    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        // We want the header to take up all spans so it fills the screen width
        return totalSpanCount;
    }

    @Override
    public void bind(MessageIssueHolder holder) {
        holder.title.setText(title);
        holder.detail.setText(detaill);
        holder.require.setText(require);
        holder.view.setOnClickListener(clickListener);
    }


    public static class MessageIssueHolder extends EpoxyHolder {
        @BindView(R2.id.message_title)
        TextView title;
        @BindView(R2.id.message_detail)
        TextView detail;
        @BindView(R2.id.person_position)
        TextView require;

        View view;

        @Override
        protected void bindView(View itemView) {
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
