package com.cinematching.application.controllers.fragments.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cinematching.application.R;
import com.cinematching.application.controllers.MainActivity;
import com.cinematching.application.controllers.fragments.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends BaseFragment implements SignInController {

    private static String fragmentTitle = "Sign in";

    private Unbinder unbinder;

    @BindView(R.id.btn_email_sign_in)
    Button buttonEmailSignIn;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public String getFragmentTitle() {
        return SignInFragment.fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        SignInFragment.fragmentTitle = fragmentTitle;
    }

    @OnClick(R.id.btn_email_sign_in)
    public void onEmailSigninButtonClicked() {
        authenticate();
    }

    @Override
    public void authenticate() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
