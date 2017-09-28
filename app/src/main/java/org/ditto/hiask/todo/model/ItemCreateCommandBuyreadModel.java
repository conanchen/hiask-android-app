package org.ditto.hiask.todo.model;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import org.ditto.hiask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

/**
 * This model shows an example of binding to a specific view type. In this case it is a custom view
 * we made, but it could also be another single view, like an EditText or Button.
 */
@EpoxyModelClass
public abstract    class ItemCreateCommandBuyreadModel extends EpoxyModelWithHolder<ItemCreateCommandBuyreadModel.ReachableHolder> {
    @EpoxyAttribute
    String title;
    @EpoxyAttribute
    String detail;


    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;


    @Override
    public void bind(ItemCreateCommandBuyreadModel.ReachableHolder holder) {
        holder.title.setText(title);
        holder.detail.setText(detail);
        holder.view.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(ItemCreateCommandBuyreadModel.ReachableHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.view.setOnClickListener(null);
    }


    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        // We want the header to take up all spans so it fills the screen width
        return totalSpanCount;
    }

    
    @Override
    protected int getDefaultLayout() {
        return R.layout.item_createcommand_buyread;
    }


    public static class ReachableHolder extends EpoxyHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.detail)
        TextView detail;

        View view;

        @Override
        protected void bindView(View itemView) {
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
