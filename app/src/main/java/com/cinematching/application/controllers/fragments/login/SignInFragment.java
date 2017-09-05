package com.cinematching.application.controllers.fragments.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinematching.application.R;
import com.cinematching.application.controllers.LoginActivity;
import com.cinematching.application.controllers.fragments.BaseFragment;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends BaseFragment {

    private static String fragmentTitle = "Sign in";

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

        return view;
    }

    @Override
    public String getFragmentTitle() {
        return SignInFragment.fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        SignInFragment.fragmentTitle = fragmentTitle;
    }

    @OnClick(R.id.btn_sign_up)
    public void onGoSignUpFragmentButtonClicked() {
        ((LoginActivity) getActivity()).switchToSignUp();
    }
}
