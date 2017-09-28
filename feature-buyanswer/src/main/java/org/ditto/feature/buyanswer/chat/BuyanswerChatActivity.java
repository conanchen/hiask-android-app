package org.ditto.feature.buyanswer.chat;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.crashlytics.android.Crashlytics;
import com.google.common.base.Strings;

import org.ditto.feature.base.BaseActivity;
import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.FragmentsPagerAdapter;
import org.ditto.feature.base.SampleItemAnimator;
import org.ditto.feature.base.VerticalGridCardSpacingDecoration;
import org.ditto.feature.base.content.actions.ActionAudioFragment;
import org.ditto.feature.base.content.actions.ActionChatMoreFragment;
import org.ditto.feature.base.content.actions.ActionGiftFragment;
import org.ditto.feature.base.content.actions.ActionImageFragment;
import org.ditto.feature.base.content.actions.ActionVideoFragment;
import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.lib.Constants;
import org.ditto.lib.dbroom.buyanswer.BuyanswerCommand;
import org.ditto.lib.dbroom.buyanswer.BuyanswerMessage;
import org.ditto.lib.dbroom.vo.VoText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/feature_buyanswer/BuyanswerChatActivity")
public class BuyanswerChatActivity extends BaseActivity
        implements BuyanswerChatController.AdapterCallbacks {

    @Autowired
    String buyanswerUuid;


    @Inject
    BuyanswerViewModelFactory viewModelFactory;
    private BuyanswerChatViewModel viewModel;


    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    @BindView(R2.id.chat_edittext)
    EditText chatEditText;

    @BindView(R2.id.chat_send)
    ImageView chatSend;

    GridLayoutManager gridLayoutManager;
    @BindView(R2.id.itemlist)
    RecyclerView recyclerView;

    private MenuItem menuQuestionInprogress;
    private MenuItem menuQuestionDone;

    private static final int SPAN_COUNT = 1;

    private final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    private final BuyanswerChatController controller = new BuyanswerChatController(this, recycledViewPool);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.buyanswer_activity_chat);

        ARouter.getInstance().inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupRecyclerView();
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        if (Strings.isNullOrEmpty(buyanswerUuid)) {
            throw new AssertionError("buyanswerUuid cannot be empty");
        }

        super.onPostCreate(savedInstanceState);
        setupController(buyanswerUuid);
    }

    private void setupRecyclerView() {
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
        gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        gridLayoutManager.setSpanSizeLookup(controller.getSpanSizeLookup());
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new VerticalGridCardSpacingDecoration());
        recyclerView.setItemAnimator(new SampleItemAnimator());
        recyclerView.setAdapter(controller.getAdapter());

    }


    @OnClick(R2.id.chat_edittext)
    public void onChatEditTextClicked(View v) {
        Observable.just(1)
                .subscribeOn(Schedulers.computation())
                .delay(200, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aInt -> {
                            if (gridLayoutManager.getItemCount() > 0) {
                                recyclerView.scrollToPosition(gridLayoutManager.getItemCount() - 1);
                            }
                        }
                );
    }

    @OnClick(R2.id.chat_send)
    public void chatSendClicked() {
        viewModel.upsertMessage(VoText.builder().setDetail(
                " chat message " + chatEditText.getText()).build());
        chatEditText.clearComposingText();
        chatEditText.setText("");
    }


    private void setupController(String buyanswerUuid) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BuyanswerChatViewModel.class);

        Map<String, Object> datas = new HashMap<>();
        datas.put(Constants.DATA1, new ArrayList<BuyanswerMessage>());
        datas.put(Constants.DATA2, new ArrayList<BuyanswerCommand>());
        controller.setData((ArrayList<BuyanswerMessage>) datas.get(Constants.DATA1)
                , (ArrayList<BuyanswerCommand>) datas.get(Constants.DATA2));

        viewModel.getLiveBuyanswerMessages().observe(this, messages -> {
            datas.put(Constants.DATA1, messages);
            controller.setData((ArrayList<BuyanswerMessage>) datas.get(Constants.DATA1)
                    , (ArrayList<BuyanswerCommand>) datas.get(Constants.DATA2));
        });
        viewModel.getLiveUpsertMessageCommands().observe(this, upsertMessageCommands -> {
            datas.put(Constants.DATA2, upsertMessageCommands);
            controller.setData((ArrayList<BuyanswerMessage>) datas.get(Constants.DATA1)
                    , (ArrayList<BuyanswerCommand>) datas.get(Constants.DATA2));
        });
        viewModel.refresh(buyanswerUuid);

    }


    private void closeKeyboard() {
        View view = this.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_buyanswer_chat, menu);
        menuQuestionInprogress = menu.findItem(R.id.menu_inprogress);
        menuQuestionDone = menu.findItem(R.id.menu_done);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.menu_inprogress == item.getItemId()) {
            showInpgressQuestionDialog();
        } else if (R.id.menu_done == item.getItemId()) {
            showDoneQuestionDialog();
        } else if (android.R.id.home == item.getItemId()) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void showInpgressQuestionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("关闭会话")
                .setMessage("你将要关闭会话，系统将扣除价值13通通币的[棒棒棒棒糖]礼物🎁给紫霞仙子")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        menuQuestionInprogress.setVisible(false);
                        menuQuestionDone.setVisible(true);
                    }
                })
                .show();

    }

    private void showDoneQuestionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("重新发起会话")
                .setMessage("你将要重新发起会话，系统将再次预先扣除价值13通通币的[棒棒棒棒糖]礼物🎁")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        menuQuestionInprogress.setVisible(true);
                        menuQuestionDone.setVisible(false);
                        ARouter.getInstance().build("/feature_buyanswer/BuyanswerArticleEditActivity")
                                .navigation();
                        BuyanswerChatActivity.this.finish();
                    }
                })
                .show();
    }


    @Override
    public void onMessageItemClicked(BuyanswerMessage carousel, int colorPosition) {
        Snackbar.make(toolbar, "TODO://----onMessageItemClicked for IndexMessage ", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onMessageCommandItemClicked(BuyanswerCommand message, int position) {
        Snackbar.make(toolbar, "TODO://----onMessageCommandItemClicked for IndexMessage Content", Snackbar.LENGTH_LONG).show();
    }

}
