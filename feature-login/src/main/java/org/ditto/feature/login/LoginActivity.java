package org.ditto.feature.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.google.gson.Gson;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.ditto.feature.login.beans.User;
import org.ditto.lib.apigrpc.LoginGrpcTask;
import org.ditto.lib.Constants;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = "/feature_login/LoginActivity")
public class LoginActivity extends AppCompatActivity {
    private String TAG = "LoginActivity";

    private static final String SDCARD_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();


    @Autowired(name="username")
    String username; //maybe passed from RegisterActivity、ForgetpasswordActivity

    @BindView(R2.id.username_layout)
    TextInputLayout usernameLayout;
    @BindView(R2.id.username)
    EditText usernameEditText;

    @BindView(R2.id.password)
    EditText passwordEditText;

    private QQLogin mQqLogin;
    private WeiboLogin mWeiboLogin;
    private WechatLogin mWechatLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R2.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ARouter.getInstance().inject(this);

        mQqLogin = new QQLogin(this);
        mWeiboLogin = new WeiboLogin(this);
        mWechatLogin = new WechatLogin(this);

        if (!TextUtils.isEmpty(username)) {
            usernameEditText.setText(username);
            passwordEditText.requestFocus();
        }
        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                usernameLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    if (usernameEditText.getText() != null && usernameEditText.getText().length() < 11) {
                        usernameLayout.setErrorEnabled(true);
                        usernameLayout.setError("请输入正确的手机号或者通通号");
                    }
                }
            }
        });
    }

    @OnClick(R2.id.register_button)
    public void onFastRegisterButtonClicked() {
        ARouter.getInstance().build("/feature_login/RegisterActivity")
                .navigation();
        finish();
    }

    @OnClick(R2.id.forgetpassword_button)
    public void onforgetPasswordButtonClicked() {
        ARouter.getInstance().build("/feature_login/ForgetpasswordActivity")
                .navigation();
        finish();
    }


    @OnClick(R2.id.login_button)
    public void loginButtonOnClick(Button button) {
        button.setEnabled(false);
    }

    @OnClick(R2.id.login_wechat_button)
    public void loginWechatOnClick(ImageButton button) {
        Log.e(TAG, "call wxapi ");
        mWechatLogin.login();
    }

    @OnClick(R2.id.login_qq_button)
    public void loginQQOnClick(ImageButton button) {
        Log.e(TAG, "call loginQQOnClick ");
        mQqLogin.login();
    }

    @OnClick(R2.id.login_weibo_button)
    public void loginWeiboOnClick(ImageView button) {
        Log.d(TAG, "call loginWeiboOnClick ");
        mWeiboLogin.login();
    }


    /***
     * QQ平台返回返回数据个体 loginListener的values
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //QQ login result
        if (requestCode == com.tencent.connect.common.Constants.REQUEST_LOGIN ||
                requestCode == com.tencent.connect.common.Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mQqLogin.mQQloginListener);
        }

        // Weibo Login Result, SSO 授权回调
        // 重要：发起 SSO 登陆的 Activity 必须重写 onActivityResults
        if (mWeiboLogin != null) {
            mWeiboLogin.authorizeCallBack(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class QQLogin {
        private Activity mActivity;
        private Tencent mTencentQQ;
        private UserInfo mInfo;
        private final String mHiaskAppidInQQ = "1106139510";


        public QQLogin(Activity loginActivity) {
            this.mActivity = loginActivity;
            if (mTencentQQ == null) {
                mTencentQQ = Tencent.createInstance(mHiaskAppidInQQ, mActivity);
            }
        }

        public void login() {
            if (!mTencentQQ.isSessionValid()) {
                mTencentQQ.login(mActivity, "all", mQQloginListener);
            }

        }


        /**
         * 获取登录QQ腾讯平台的权限信息(用于访问QQ用户信息)
         *
         * @param jsonObject
         */
        public void initOpenidAndToken(JSONObject jsonObject) {
//        public static void initOpenidAndToken(JSONObject jsonObject) {
            try {
                String token = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
                String expires = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN);
                String openId = jsonObject.getString(com.tencent.connect.common.Constants.PARAM_OPEN_ID);
                if (!android.text.TextUtils.isEmpty(token) && !android.text.TextUtils.isEmpty(expires)
                        && !android.text.TextUtils.isEmpty(openId)) {
                    mTencentQQ.setAccessToken(token, expires);
                    mTencentQQ.setOpenId(openId);
                }
                Log.i("enyu", "token:" + token);
                Log.i("enyu", "expires:" + expires);
                Log.i("enyu", "openId:" + openId);

                new LoginGrpcTask(token).execute();
            } catch (Exception e) {
            }
        }

        private void updateUserInfo() {
            if (mTencentQQ != null && mTencentQQ.isSessionValid()) {
                IUiListener listener = new IUiListener() {
                    @Override
                    public void onError(UiError e) {
                    }

                    @Override
                    public void onComplete(final Object response1) {
                        JSONObject response = (JSONObject) response1;
                        if (response.has("nickname")) {
                            Gson gson = new Gson();
                            User user = gson.fromJson(response.toString(), User.class);
                            if (user != null) {
                                Log.i("enyu", "昵称：" + user.getNickname() + "  性别:" + user.getGender() + "  地址：" + user.getProvince() + user.getCity());
                                Log.i("enyu", "头像路径：" + user.getFigureurl_qq_2());
                            }
                        }
                    }

                    @Override
                    public void onCancel() {
                    }
                };
                mInfo = new UserInfo(mActivity, mTencentQQ.getQQToken());
                mInfo.getUserInfo(listener);
            }
        }

        /**
         * 继承的到BaseUiListener得到doComplete()的方法信息
         */
        IUiListener mQQloginListener = new QQBaseUiListener() {
            @Override
            protected void doComplete(JSONObject values) {//得到用户的ID  和签名等信息  用来得到用户信息
                Log.i("enyu", values.toString());
                initOpenidAndToken(values);
                updateUserInfo();
            }
        };

        private class QQBaseUiListener implements IUiListener {
            @Override
            public void onComplete(Object response) {
                if (null == response) {
                    Toast.makeText(mActivity, "QQBaseUiListener登录失败", Toast.LENGTH_LONG).show();
                    return;
                }
                JSONObject jsonResponse = (JSONObject) response;
                if (null != jsonResponse && jsonResponse.length() == 0) {
                    Toast.makeText(mActivity, "QQBaseUiListener登录失败", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(mActivity, "QQBaseUiListener登录成功", Toast.LENGTH_LONG).show();
                doComplete((JSONObject) response);
            }

            protected void doComplete(JSONObject values) {
                //// TODO: 2017/5/25
            }

            @Override
            public void onError(UiError e) {
                //登录错误
                Log.i("enyu", "onError");
            }

            @Override
            public void onCancel() {
                // 运行完成
                Log.i("enyu", "onCancel");
            }
        }

    }

    private class WeiboLogin {
        private Activity mActivity;

        /**
         * 注意：SsoHandler 仅当 SDK 支持 SSO 时有效
         */
        private SsoHandler mWeiboSsoHandler;
        /**
         * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
         */
        private Oauth2AccessToken mAccessToken;

        public WeiboLogin(Activity loginActivity) {
            this.mActivity = loginActivity;
            WbSdk.install(mActivity, new AuthInfo(mActivity, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
            mWeiboSsoHandler = new SsoHandler(mActivity);

        }

        public void login() {
            mWeiboSsoHandler.authorize(new SelfWeiboAuthListener());
        }

        public void authorizeCallBack(int requestCode, int resultCode, Intent data) {
            mWeiboSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

        private class SelfWeiboAuthListener implements com.sina.weibo.sdk.auth.WbAuthListener {

            @Override
            public void onSuccess(final Oauth2AccessToken token) {
                mAccessToken = token;
                if (mAccessToken.isSessionValid()) {
                    // 显示 Token
//                    updateTokenView(false);
                    // 保存 Token 到 SharedPreferences
                    AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                    String token1 = mAccessToken.getToken();
                    String refreshtoken = mAccessToken.getRefreshToken();
                    String uid = mAccessToken.getUid();
                    String phoneNum = mAccessToken.getPhoneNum();
                    Log.i("enyu", "token1:" + token1);
                    Log.i("enyu", "refreshtoken:" + refreshtoken);
                    Log.i("enyu", "uid:" + uid);
                    Log.i("enyu", "phoneNum:" + phoneNum);
                }
            }

            @Override
            public void cancel() {
                Log.i("enyu", "cancel");
            }

            @Override
            public void onFailure(WbConnectErrorMessage errorMessage) {
                Log.i("enyu", "onFailure");
            }
        }

    }

    private class WechatLogin {
        private final Activity mActivity;
        private IWXAPI mWeixinAPI;

        public WechatLogin(Activity loginActivity) {
            this.mActivity = loginActivity;

            mWeixinAPI = WXAPIFactory.createWXAPI(mActivity, Constants.APP_ID);
            mWeixinAPI.registerApp(Constants.APP_ID);
        }

        public void login() {
            // send oauth request
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "none";
            mWeixinAPI.sendReq(req);
        }


        IWXAPIEventHandler mWeixinapiEventHandler = new IWXAPIEventHandler() {

            // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
            @Override
            public void onResp(BaseResp resp) {
                int result = 0;

                Log.e(TAG, "baseresp.getType = " + resp.getType());
//            Toast.makeText(this, "baseresp.getType = " + resp.getType(), Toast.LENGTH_SHORT).show();

                switch (resp.errCode) {
                    case BaseResp.ErrCode.ERR_OK:
                        result = R.string.errcode_success;
                        break;
                    case BaseResp.ErrCode.ERR_USER_CANCEL:
                        result = R.string.errcode_cancel;
                        break;
                    case BaseResp.ErrCode.ERR_AUTH_DENIED:
                        result = R.string.errcode_deny;
                        break;
                    case BaseResp.ErrCode.ERR_UNSUPPORT:
                        result = R.string.errcode_unsupported;
                        break;
                    default:
                        result = R.string.errcode_unknown;
                        break;
                }

//            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onReq(BaseReq baseReq) {

            }
        };

    }
}
