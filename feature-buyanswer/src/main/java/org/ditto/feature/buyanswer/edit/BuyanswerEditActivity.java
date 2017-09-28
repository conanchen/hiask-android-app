package org.ditto.feature.buyanswer.edit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.common.base.Strings;

import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.BaseFragmentActivity;
import org.ditto.feature.base.FragmentsPagerAdapter;
import org.ditto.feature.buyanswer.R;
import org.ditto.feature.buyanswer.R2;
import org.ditto.feature.buyanswer.di.BuyanswerViewModelFactory;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepContent;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepGeofence;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepGift;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepPreview;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepTime2finish;
import org.ditto.feature.buyanswer.edit.fragments.FragmentEditstepTitle;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@Route(path = "/feature_buyanswer/BuyanswerEditActivity")
public class BuyanswerEditActivity extends BaseFragmentActivity {

    @Autowired
    String buyanswerUuid;


    @Inject
    BuyanswerViewModelFactory viewModelFactory;
    private BuyanswerEditViewModel viewModel;

    private final int QuestionPreviewStepFragmentPosition = 5;

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.view_pager)
    ViewPager contentViewPager;
    private MenuItem menuQuestionNextstep;
    private MenuItem menuQuestionPublish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.buyanswer_activity_edit);

        ARouter.getInstance().inject(this);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupContentViewPager();

    }

    private void setupContentViewPager() {
        List<BaseFragment> fmList = new ArrayList<>();
        fmList.add(FragmentEditstepTitle.builder().title("编辑问题标题").build());
        fmList.add(FragmentEditstepContent.builder().title("编辑问题内容").build());
        fmList.add(FragmentEditstepTime2finish.builder().title("设置回答时限").build());
        fmList.add(FragmentEditstepGeofence.builder().title("设置回答区域").build());
        fmList.add(FragmentEditstepGift.builder().title("设置回答赏金").build());
        fmList.add(FragmentEditstepPreview.builder().title("预览有赏问题").build());
        FragmentsPagerAdapter fmAapter = new FragmentsPagerAdapter(this.getSupportFragmentManager(), fmList);

        contentViewPager.setAdapter(fmAapter);
        contentViewPager.setCurrentItem(0, true);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        if (Strings.isNullOrEmpty(buyanswerUuid)) {
            throw new AssertionError("buyanswerUuid cannot be empty");
        }
        super.onPostCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BuyanswerEditViewModel.class);
        Timber.d("viewModel=%s passed by ARouter buyanswerUuid=%s", viewModel, buyanswerUuid);
        viewModel.refresh(buyanswerUuid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_buyanswer_edit, menu);
        menuQuestionNextstep = menu.findItem(R.id.menu_nextstep);
        menuQuestionPublish = menu.findItem(R.id.menu_send);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Timber.d("item.getItemId()=%x ?= R.id.menu_nextstep=%x ?= R2.id.menu_nextstep=%x contentViewPager.getCurrentItem()=%d",
                item.getItemId(), R.id.menu_nextstep, R2.id.menu_nextstep, contentViewPager.getCurrentItem());
        if (R.id.menu_nextstep == item.getItemId()) {
            int position = contentViewPager.getCurrentItem() + 1;
            contentViewPager.setCurrentItem(position);
            updateMenuByStep(position);
        } else if (R.id.menu_send == item.getItemId()) {
            showPublishQuestionDialog();
        } else if (android.R.id.home == item.getItemId()) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (contentViewPager.getCurrentItem() > 0) {
            int moveToPositon = contentViewPager.getCurrentItem() - 1;
            contentViewPager.setCurrentItem(moveToPositon);
            menuQuestionNextstep.setVisible(true);
            menuQuestionPublish.setVisible(false);
            return;
        }
        super.onBackPressed();
    }

    private void showPublishQuestionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发布问题")
                .setMessage("你将要发布问题让指定范围的公众回答，系统将预先扣除价值13通通币的[棒棒棒棒糖]礼物🎁")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewModel.publish().subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {
                                viewModel.refresh();
                                menuQuestionPublish.setVisible(false);
                                BuyanswerEditActivity.this.finish();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                Timber.e(throwable);
                            }
                        });
                    }
                })
                .show();
    }


    private void updateMenuByStep(int position) {
        menuQuestionNextstep.setVisible(false);
        menuQuestionPublish.setVisible(false);
        switch (position) {
            case QuestionPreviewStepFragmentPosition:
                menuQuestionPublish.setVisible(true);
                break;
            default:
                menuQuestionNextstep.setVisible(true);
        }
    }
}
