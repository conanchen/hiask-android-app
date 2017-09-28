package org.ditto.feature.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ditto.feature.login.controllers.ForgetpasswordController;
import org.ditto.feature.base.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgetpasswordNewFragment extends BaseFragment {
    private ForgetpasswordController.Callbacks callbacks;

    public ForgetpasswordNewFragment() {
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
        if (context instanceof ForgetpasswordController.Callbacks) {
            callbacks = (ForgetpasswordController.Callbacks) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ForgetpasswordController.ContentCallbacks");
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

    public static ForgetpasswordNewFragment.Builder builder() {
        return new ForgetpasswordNewFragment.Builder();
    }

    public static final class Builder {
        private String title;


        Builder() {
        }


        public ForgetpasswordNewFragment.Builder title(String value) {
            this.title = value;
            return this;
        }


        public ForgetpasswordNewFragment build() {
            String missing = "";
            if (title == null) {
                missing += " title";
            }

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + missing);
            }
            ForgetpasswordNewFragment fragment = new ForgetpasswordNewFragment();
            fragment.setTitle(title);
            return fragment;
        }
    }

}
