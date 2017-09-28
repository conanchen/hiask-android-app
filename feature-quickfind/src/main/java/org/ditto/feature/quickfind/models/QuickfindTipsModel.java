package org.ditto.feature.quickfind.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;


import org.ditto.feature.quickfind.R;
import org.ditto.feature.quickfind.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

@EpoxyModelClass
public  abstract class QuickfindTipsModel extends EpoxyModelWithHolder<QuickfindTipsModel.QuickfindTipHolder> {

    @EpoxyAttribute
    String detail;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;


    @Override
    public void bind(QuickfindTipHolder holder) {
        holder.questionContentText.setText(detail);
        holder.view.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(QuickfindTipHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.questionContentText.setOnClickListener(null);
    }


    @Override
    protected int getDefaultLayout() {
        return R.layout.quickfind_tips_model;
    }

    public static class QuickfindTipHolder extends EpoxyHolder {
        @BindView(R2.id.tips_text)
        TextView questionContentText;

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
