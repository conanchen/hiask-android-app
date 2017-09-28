package org.ditto.feature.buyanswer.edit.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.FragmentsPagerAdapter;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.SoftKeyBoardListener;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.buyanswer.edit.BuyanswerEditViewModel;
import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.feature.base.content.actions.ActionAudioFragment;
import org.ditto.feature.base.content.actions.ActionEditMoreFragment;
import org.ditto.feature.base.content.actions.ActionImageFragment;
import org.ditto.feature.base.content.actions.ActionVideoFragment;
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
public class FragmentEditstepContent extends BaseFragment
        implements ControllerEditstepContent.AdapterCallbacks {

    @Inject
    BuyanswerViewModelFactory viewModelFactory;
    private BuyanswerEditViewModel viewModel;

    GridLayoutManager gridLayoutManager;

    @BindView(R2.id.include)
    ViewGroup chatInputLayout;

    @BindView(R2.id.chat_edittext)
    EditText chatEditText;

    @BindView(R2.id.chat_send)
    ImageView chatSend;


    @BindView(R2.id.chat_action_view_pager)
    ViewPager actionViewPager;
    @BindView(R2.id.toolbar_tab)
    TabLayout mTabLayout;

    private static final int ACTION_MORE_POSITION = 3;
    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;

    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final ControllerEditstepContent controller = new ControllerEditstepContent(this, recycledViewPool);
    private FragmentActivity mContext;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentEditstepContent() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity();
        setupController();

        SoftKeyBoardListener.setListener(mContext, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int visibleHeight, int keyboardHeight) {

            }

            @Override
            public void keyBoardHide(int visibleHeight, int keyboardHeight) {
                int extraHeight = chatEditText.getLayoutParams().height + mTabLayout.getLayoutParams().height;

//                ConstraintLayout.LayoutParams layoutParams1 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//                layoutParams1.height = visibleHeight - extraHeight;
//                recyclerView.setLayoutParams(layoutParams1);
//
//                ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//                layoutParams2.height = keyboardHeight+extraHeight;
//                layoutParams2.width = chatInputLayout.getWidth();
//                chatInputLayout.setLayoutParams(layoutParams2);
//
//
//                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//                layoutParams.height = keyboardHeight;
//                layoutParams.width = actionViewPager.getWidth();
//                actionViewPager.setLayoutParams(layoutParams);
            }
        });
    }

    private void setupController() {
        Timber.d("calling setupController");
        viewModel = ViewModelProviders.of(this.getActivity(), viewModelFactory).get(BuyanswerEditViewModel.class);

        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, new Buyanswer());
        datas.put(Constants.DATA2, new ArrayList<BuyanswerContent>());
        datas.put(Constants.DATA3, new BuyanswerCommand());
        datas.put(Constants.DATA4, new ArrayList<BuyanswerCommand>());

        viewModel.getLiveBuyanswer().observe(this, buyanswer -> {
            datas.put(Constants.DATA1, buyanswer);
            controller.setData((Buyanswer) datas.get(Constants.DATA1),
                    (List<BuyanswerContent>) datas.get(Constants.DATA2),
                    (BuyanswerCommand) datas.get(Constants.DATA3),
                    (List<BuyanswerCommand>) datas.get(Constants.DATA4));
        });
        viewModel.getLiveBuyanswerContents().observe(this, buyanswerContents -> {
            datas.put(Constants.DATA2, buyanswerContents);
            controller.setData((Buyanswer) datas.get(Constants.DATA1),
                    (List<BuyanswerContent>) datas.get(Constants.DATA2),
                    (BuyanswerCommand) datas.get(Constants.DATA3),
                    (List<BuyanswerCommand>) datas.get(Constants.DATA4));
        });
        viewModel.getLiveBuyanswerCreateCommand().observe(this, buyanswerCreateCommand -> {
            datas.put(Constants.DATA3, buyanswerCreateCommand);
            controller.setData((Buyanswer) datas.get(Constants.DATA1),
                    (List<BuyanswerContent>) datas.get(Constants.DATA2),
                    (BuyanswerCommand) datas.get(Constants.DATA3),
                    (List<BuyanswerCommand>) datas.get(Constants.DATA4));
        });
        viewModel.getLiveUpsertContentCommands().observe(this, upsertContentCommands -> {
            datas.put(Constants.DATA4, upsertContentCommands);
            controller.setData((Buyanswer) datas.get(Constants.DATA1),
                    (List<BuyanswerContent>) datas.get(Constants.DATA2),
                    (BuyanswerCommand) datas.get(Constants.DATA3),
                    (List<BuyanswerCommand>) datas.get(Constants.DATA4));
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buyanswer_fragment_editstep_content, container, false);
        ButterKnife.bind(this, view);
        // Set the adapter

        // Many carousels and color models are shown on screen at once. The default recycled view
        // pool size is only 5, so we manually set the pool size to avoid constantly creating new views
        // We also use a shared view pool so that carousels can recycle items between themselves.
//        recycledViewPool.setMaxRecycledViews(R.layout.model_color, Integer.MAX_VALUE);
//        recycledViewPool.setMaxRecycledViews(R.layout.model_carousel_group, Integer.MAX_VALUE);
        recyclerView.setRecycledViewPool(recycledViewPool);

        // We are using a multi span grid to allow two columns of buttons. To set this up we need
        // to set our span count on the controller and then get the span size lookup object from
        // the controller. This look up object will delegate span size lookups to each model.
        controller.setSpanCount(SPAN_COUNT);
        gridLayoutManager = new GridLayoutManager(this.getContext(), SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());


        setupChatActions();


        return view;
    }


    @OnFocusChange(R2.id.chat_edittext)
    public void onChatEditTextFocusChanged(View v, boolean hasFocus) {
        if (hasFocus) {
            actionViewPager.setVisibility(View.GONE);
        }

    }

    @OnClick(R2.id.chat_edittext)
    public void onChatEditTextClicked(View v) {
        actionViewPager.setVisibility(View.GONE);
    }

    @OnClick(R2.id.chat_send)
    public void chatSendClicked() {

        viewModel.upsertContent(VoText.builder()
                .setDetail(" chat message " + chatEditText.getText()
                        + " gridLayoutManager.getItemCount()="
                        + gridLayoutManager.getItemCount())
                .build());
        chatEditText.clearComposingText();
        chatEditText.setText("");
    }


    private void setupChatActions() {
        List<BaseFragment> fmList = new ArrayList<>();
        fmList.add(ActionAudioFragment.builder().title("音频").build());
        fmList.add(ActionImageFragment.builder().title("图片").build());
        fmList.add(ActionVideoFragment.builder().title("视频").build());
        fmList.add(ActionEditMoreFragment.builder().title("更多").build());
        FragmentsPagerAdapter fmAapter = new FragmentsPagerAdapter(this.getFragmentManager(), fmList);

        actionViewPager.setAdapter(fmAapter);
        mTabLayout.setupWithViewPager(actionViewPager);
        final int icons[] = {
                R2.drawable.ic_home_black_24dp,
                R2.drawable.ic_arrow_forward_black_24dp,
                R2.drawable.ic_my_location_black_24dp,
                R2.drawable.ic_library_add_black_24dp
        };

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(icons[i]);
            mTabLayout.getTabAt(i).setText("");
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tabSelected(tab);
            }

            private void tabSelected(TabLayout.Tab tab) {
                actionViewPager.setVisibility(View.VISIBLE);
                closeKeyboard();
                if (tab.getPosition() == ACTION_MORE_POSITION) {
                    if ("selected".equals(tab.getTag())) {
                        tab.setIcon(icons[ACTION_MORE_POSITION]);
                        tab.setTag("unselected");

                    } else {
                        tab.setIcon(R2.drawable.ic_menu_black_24dp);
                        tab.setTag("selected");
                        actionViewPager.setVisibility(View.GONE);
                    }
                }

            }
        });

    }

    private void closeKeyboard() {
        View view = this.getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerView.setAdapter(null);
    }

    @Override
    public void onItemCommandClicked(BuyanswerCommand carousel, int colorPosition) {

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


        public FragmentEditstepContent build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            FragmentEditstepContent fragment = new FragmentEditstepContent();
            fragment.setTitle(title);
            return fragment;
        }
    }
}
