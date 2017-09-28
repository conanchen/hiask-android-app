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


@EpoxyModelClass
public abstract class ItemCommandUpsertContentTextModel extends EpoxyModelWithHolder<ItemCommandUpsertContentTextModel.Holder> {

    @EpoxyAttribute
    String detail;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;


    @Override
    public void bind(Holder holder) {
        holder.questionContentText.setText(String.format(detail));
        holder.view.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(Holder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.questionContentText.setOnClickListener(null);
    }

    public static class Holder extends EpoxyHolder {
        @BindView(R2.id.question_content_text)
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

    @Override
    protected int getDefaultLayout() {
        return R.layout.buyanswer_item_command_upsertcontent_text_model;
    }

}
