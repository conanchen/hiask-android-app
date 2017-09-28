package org.ditto.feature.test;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xdandroid.hellodaemon.IntentWrapper;

import org.ditto.feature.test.di.TestViewModelFactory;
import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.di.Injectable;
import org.ditto.lib.apirest.data.GitUser;
import org.ditto.lib.dbroom.misc.Gift;
import org.ditto.lib.dbroom.vo.VoGeofence;
import org.ditto.lib.dbroom.vo.VoLocation;
import org.ditto.lib.usecases.AppServiceKeepliveTraceImpl;
import org.ditto.feature.base.model.LocationAmapLiveData;
import org.ditto.feature.base.model.Question;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class TestFragment extends BaseFragment implements Injectable {
    private static final String LOGIN_KEY = "login";
    @Inject
    TestViewModelFactory viewModelFactory;

    private TestViewModel testViewModel;

    @BindView(R2.id.name)
    TextView userName;
    @BindView(R2.id.txt_keeplive)
    TextView mTxtKeeplive;

    public static TestFragment create(String login) {
        TestFragment userFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(LOGIN_KEY, login);
        userFragment.setArguments(bundle);
        userFragment.setTitle(login);
        return userFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.user_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        testViewModel = ViewModelProviders.of(this, viewModelFactory).get(TestViewModel.class);
        testViewModel.setLogin(getArguments().getString(LOGIN_KEY));
        testViewModel.getUser().observe(this, (userResource) -> {
            if (userResource != null && userResource.data != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(userResource.data.getCreated());
                Date created = calendar.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss ");
                userName.setText(userResource.data.getName() + "created at " + sdf.format(created));
            } else {
                userName.setText("hardcoded conanchen");
            }
        });

        LocationAmapLiveData.get(getActivity()).observe(this, (location) -> {


            //TODO: should first testsaveapinouse to db, then autonitify to update controller
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmm");
            VoLocation mylocation = new VoLocation(location.getLatitude(), location.getLongitude(),
                    System.currentTimeMillis(), "conan");

            String now = sdf.format(new Date());
            String nowText = now + " 云巴目前使用的Cache集群是Couchbase集群和Redis集群。其中Couchbase可以让数据自动在多个节点备份，单节点失效不会影响业务，而且支持业务自动分片（autosharing）。所谓自动分片，就是把同类型的业务自动分配到不同的机器上。";
            Question question = new Question(nowText, "tag1,tag2,tag3", location.getLatitude(), location.getLongitude(), "conan");
            question.setUuid("q" + System.currentTimeMillis());
            question.setFenceCircle(VoGeofence.builder()
                    .setCenterAddress("zhongxinweizhi")
                    .setLat(location.getLatitude())
                    .setLon(location.getLongitude())
                    .setRadius(500)
                    .build());
            String name = sdf1.format(new Date());
            question.setGift(Gift.builder().setUuid("uuid2joiasdf222").setIcon("gitf" + name)
                    .setName("棒棒棒糖" + name).setPrice(123).build());
            testViewModel.updateUserLatestLocation(mylocation)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            saveId -> {
                                mTxtKeeplive.setText(
                                        String.format("LocationAmapLiveData  lat=%f, lon=%f at %s",
                                                mylocation.getLat(), mylocation.getLon(), now));
                            },
                            throwable -> {
                                mTxtKeeplive.setText("Failed to save UpdateLatestLocation command...");

                            }
                    );
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        // clear all the subscription
        mDisposable.clear();
    }

    @OnClick(R2.id.retry)
    public void onRetryClick(Button button) {
        testViewModel.retry();
    }

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @OnClick(R2.id.retryrx)
    public void onRetryRxClick(Button button) {
        mDisposable.add(testViewModel.getRxUser(getArguments().getString(LOGIN_KEY))
                .subscribe(
                        new Consumer<GitUser>() {
                            @Override
                            public void accept(GitUser user) throws Exception {
                                userName.setText("GithubRX get user.name=" + user.getName());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                userName.setText("Unable to update username" + throwable.getMessage());
                            }
                        }
                ));
    }


    @OnClick(R2.id.btn_start)
    public void onStartClick(Button button) {
        Timber.d("onStartClick startService");
        try {
            Intent intent = new Intent(this.getActivity(), AppServiceKeepliveTraceImpl.class);
            intent.setAction(Intent.ACTION_RUN);
            this.getActivity().startService(intent);
        } catch (Exception ignored) {
        }
    }

    @OnClick(R2.id.btn_white)
    public void onWhitelistClieck(Button button) {
        IntentWrapper.whiteListMatters(this.getActivity(), "轨迹跟踪服务的持续运行");
    }

    @OnClick(R2.id.btn_stop)
    public void onStopClieck(Button button) {
        AppServiceKeepliveTraceImpl.stopService();
    }

    @OnClick(R2.id.routemodule)
    public void onRoutemoduleClicked(Button button) {
        ARouter.getInstance().build("/feature_login/Login2Activity").navigation();
    }

}