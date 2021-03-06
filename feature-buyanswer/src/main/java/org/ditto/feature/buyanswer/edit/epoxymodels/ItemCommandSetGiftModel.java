package org.ditto.feature.buyanswer.edit.epoxymodels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

/**
 * This model shows an example of binding to a specific view type. In this case it is a custom view
 * we made, but it could also be another single view, like an EditText or Button.
 */
@EpoxyModelClass
public abstract class ItemCommandSetGiftModel extends EpoxyModelWithHolder<ItemCommandSetGiftModel.Holder> {

    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    String detail;
    @EpoxyAttribute
    int price;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.buyanswer_item_command_setgift_model;
    }

    @Override
    public void bind(Holder holder) {
        holder.giftName.setText(name);
        holder.giftPrice.setText(String.format("%d通通币", price));
        holder.giftDetail.setText(String.format("准备：设定[%s]为问题赏金礼物,你真是一个吝啬/爽快/壕爽的人", name));
        holder.view.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(Holder holder) {
        super.unbind(holder);
        holder.view.setOnClickListener(null);
    }

    public static class Holder extends EpoxyHolder {
        @BindView(R2.id.gift_icon)
        ImageView icon;
        @BindView(R2.id.gift_name)
        TextView giftName;
        @BindView(R2.id.gift_price)
        TextView giftPrice;
        @BindView(R2.id.gift_detail)
        TextView giftDetail;


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
}
