package org.ditto.feature.buyanswer.edit.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.common.base.Strings;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.FragmentsPagerAdapter;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.SoftKeyBoardListener;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.base.content.actions.ActionAudioFragment;
import org.ditto.feature.base.content.actions.ActionEditMoreFragment;
import org.ditto.feature.base.content.actions.ActionImageFragment;
import org.ditto.feature.base.content.actions.ActionVideoFragment;
import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.Buyanswer;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerContent;
import org.ditto.lib.dbroom.vo.VoText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import timber.log.Timber;

/**
 * A fragment representing a listCommandsBy of Items.
 * <p/>
 */
public class FragmentEditstepTitle extends BaseFragment {

    @Inject
    BuyanswerViewModelFactory viewModelFactory;
    private BuyanswerEditViewModel viewModel;

    @BindView(R2.id.edit_title)
    EditText editTitle;
    @BindView(R2.id.save_button)
    Button saveButton;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentEditstepTitle() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupController();
    }

    private void setupController() {
        Timber.d("calling setupController");
        viewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(BuyanswerEditViewModel.class);
        viewModel.getLiveUpdateTitleCommand().observe(this, updateTitleCommand -> {
            if (updateTitleCommand != null
                    && updateTitleCommand.content != null
                    && updateTitleCommand.content.updateTitle != null) {
                editTitle.setText(updateTitleCommand.content.updateTitle.title);
            }
        });
        viewModel.refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buyanswer_fragment_editstep_title, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R2.id.save_button)
    public void onSaveButtonClicked() {
        if (!Strings.isNullOrEmpty(editTitle.getText().toString())) {
            viewModel.updateTitle(editTitle.getText().toString());
        }
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


        public FragmentEditstepTitle build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            FragmentEditstepTitle fragment = new FragmentEditstepTitle();
            fragment.setTitle(title);
            return fragment;
        }
    }
}
