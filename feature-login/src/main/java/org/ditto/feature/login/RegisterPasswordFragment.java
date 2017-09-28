package org.ditto.feature.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ditto.feature.login.controllers.RegisterController;
import org.ditto.feature.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterPasswordFragment extends BaseFragment {
    private RegisterController.Callbacks callbacks;

    public RegisterPasswordFragment() {
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
        View view = inflater.inflate(R.layout.register_password_fragment, container, false);
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

    @OnClick(R2.id.changepassword_button)
    public void onChangepasswordButtonClicked() {
        callbacks.onPasswordDone("83812345678");
    }

    public static RegisterPasswordFragment.Builder builder() {
        return new RegisterPasswordFragment.Builder();
    }

    public static final class Builder {
        private String title;


        Builder() {
        }

        public RegisterPasswordFragment.Builder title(String value) {
            this.title = value;
            return this;
        }



        public RegisterPasswordFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            RegisterPasswordFragment fragment = new RegisterPasswordFragment();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
