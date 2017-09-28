package org.ditto.feature.base.content.models;

import android.view.View;
import android.widget.TextView;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;


import org.ditto.feature.base.R;
import org.ditto.feature.base.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;


@EpoxyModelClass
public abstract class ChatContentTextModel extends EpoxyModelWithHolder<ChatContentTextModel.QuestionContentTextHolder> {

    @EpoxyAttribute
    String detail;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;


    @Override
    public void bind(QuestionContentTextHolder holder) {
        holder.questionContentText.setText(String.format(detail));
        holder.questionContentText.setOnClickListener(clickListener);
    }

    @Override
    public void unbind(QuestionContentTextHolder holder) {
        // Release resources and don't leak listeners as this view goes back to the view pool
        holder.questionContentText.setOnClickListener(null);
    }

    public static class QuestionContentTextHolder extends EpoxyHolder {
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
        return R.layout.chat_content_model;
    }
}
