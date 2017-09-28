package org.ditto.feature.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import org.ditto.feature.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterOkFragment extends BaseFragment {


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface Callbacks {
        void onRegisterButtonClicked(Uri uri);
    }

    private Callbacks callbacks;


    public RegisterOkFragment callbacks(Callbacks value) {
        this.callbacks = value;
        return this;
    }

    public RegisterOkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.register_ok_fragment, container, false);

        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @OnClick(R2.id.login_button)
    public void onLoginButtonClicked(Button button){
        ARouter.getInstance().build("/feature_login/LoginActivity")
                .withString("username", "838 1234 5678")
                .navigation();
    }

    public static RegisterOkFragment.Builder builder() {
        return new RegisterOkFragment.Builder();
    }

    public static final class Builder {
        private String title;

        private Callbacks callbacks;

        Builder() {
        }

        public RegisterOkFragment.Builder callbacks(Callbacks value) {
            this.callbacks = value;
            return this;
        }

        public RegisterOkFragment.Builder title(String value) {
            this.title = value;
            return this;
        }


        public RegisterOkFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            RegisterOkFragment fragment = new RegisterOkFragment();
            fragment.callbacks(callbacks).setTitle(title);
            return fragment;
        }
    }

}
