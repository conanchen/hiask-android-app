package org.ditto.feature.base.content.actions;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.R;
import org.ditto.feature.base.R2;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A fragment representing a list of Items.
 * <p/>
 */
public class ActionEditMoreFragment extends BaseFragment {
    View view;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ActionEditMoreFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (View) inflater.inflate(R.layout.fragment_action_editmore, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick(R2.id.action_more_position)
    public void onPositionClicked() {
        Snackbar.make(view, "R2.id.action_more_position clicked", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof ArticleController.ContentCallbacks) {
//            contentCallbacks = (ArticleController.ContentCallbacks) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement ActionMoresController.ContentCallbacks");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        contentCallbacks = null;
//
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String title;

        Builder() {
        }

        public Builder title(String value) {
            this.title = value;
            return this;
        }

        public ActionEditMoreFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }
            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            ActionEditMoreFragment fragment = new ActionEditMoreFragment();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
