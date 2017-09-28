package org.ditto.feature.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import org.ditto.feature.login.controllers.RegisterController;
import org.ditto.feature.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterUsernameFragment extends BaseFragment {
    private RegisterController.Callbacks callbacks;

    @BindView(R2.id.register_username)
    AutoCompleteTextView mRegisterUsernameText;


    public RegisterUsernameFragment() {
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
        View view = inflater.inflate(R.layout.register_username_fragment, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof RegisterController.Callbacks) {
            callbacks = (RegisterController.Callbacks) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement RegisterController.ContentCallbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @OnClick(R2.id.register_button)
    public void onRegisterButtonClicked() {
        //TODO: call server api to register username+smsauthcode
        boolean ok = true;
        if (ok) {
            callbacks.onUsernameDone(mRegisterUsernameText.getText().toString());
        }
    }

    public static RegisterUsernameFragment.Builder builder() {
        return new RegisterUsernameFragment.Builder();
    }

    public static final class Builder {
        private String title;


        Builder() {
        }


        public RegisterUsernameFragment.Builder title(String value) {
            this.title = value;
            return this;
        }


        public RegisterUsernameFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            RegisterUsernameFragment fragment = new RegisterUsernameFragment();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
