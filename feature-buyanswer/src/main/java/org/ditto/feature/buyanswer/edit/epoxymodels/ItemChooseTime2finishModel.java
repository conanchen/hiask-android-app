package org.ditto.feature.buyanswer.edit.epoxymodels;

import android.view.View;
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
public abstract class ItemChooseTime2finishModel extends EpoxyModelWithHolder<ItemChooseTime2finishModel.QuestionTimeHolder> {
    @EpoxyAttribute
    long time;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    protected int getDefaultLayout() {
        return R.layout.buyanswer_item_choose_time2finish_model;
    }

    @Override
    public void bind(QuestionTimeHolder holder) {
        holder.questionTime.setText(String.valueOf(time));
        holder.questionTime.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(QuestionTimeHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.questionTime.setOnClickListener(null);
    }

    public static class QuestionTimeHolder extends EpoxyHolder {
        @BindView(R2.id.question_time)
        TextView questionTime;


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
}
