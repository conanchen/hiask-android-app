package org.ditto.feature.login;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.alibaba.android.arouter.facade.annotation.Route;


import org.ditto.feature.login.controllers.ForgetpasswordController;
import org.ditto.feature.base.BaseFragment;
import org.ditto.feature.base.FragmentsPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/feature_login/ForgetpasswordActivity")
public class ForgetpasswordActivity extends AppCompatActivity implements ForgetpasswordController.Callbacks
{

    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager();
    }

    private void setupViewPager() {
        List<BaseFragment> fmList = new ArrayList<>();
        fmList.add(ForgetpasswordUsernameFragment.builder().title("输入手机号").build());
        fmList.add(ForgetpasswordNewFragment.builder().title("修改登录密码").build());
        fmList.add(ForgetpasswordOkFragment.builder().title("修改密码成功").build());

        FragmentsPagerAdapter fmAapter = new FragmentsPagerAdapter(this.getSupportFragmentManager(), fmList);

        viewPager.setAdapter(fmAapter);
        viewPager.setCurrentItem(0, true);

    }

    @Override
    public void onUsernameDone(String username) {
        viewPager.setCurrentItem(1);
    }

    @Override
    public void onPasswordDone(String username) {
        viewPager.setCurrentItem(2);
    }
}
