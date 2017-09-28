package org.ditto.feature.my.myprofile.index.models;

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
public abstract class AccountModel extends EpoxyModelWithHolder<AccountModel.Holder> {
    @EpoxyAttribute
    String name;
    @EpoxyAttribute
    String accountNo;
    @EpoxyAttribute
    String qrcode;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener iconClickListener;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener nickNameClickListener;


    @Override
    public void bind(Holder holder) {
        holder.name.setText(name);
        holder.account_no.setText(accountNo);
        holder.name.setOnClickListener(nickNameClickListener);
    }

    @Override
    public void unbind(Holder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.name.setOnClickListener(null);
    }


    @Override
    public int getSpanSize(int totalSpanCount, int position, int itemCount) {
        // We want the header to take up all spans so it fills the screen width
        return totalSpanCount;
    }


    public static class Holder extends EpoxyHolder {
        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.account_no)
        TextView account_no;
        @BindView(R2.id.qrcode)
        ImageView qrcode;


        View view;

        @Override
        protected void bindView(View itemView) {
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }


    @Override
    protected int getDefaultLayout() {
        return R.layout.myprofie_account_view;
    }

}
