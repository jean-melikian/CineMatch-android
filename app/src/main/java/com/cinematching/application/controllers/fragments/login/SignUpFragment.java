package com.cinematching.application.controllers.fragments.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinematching.application.R;
import com.cinematching.application.controllers.fragments.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment {

    private static String fragmentTitle = "Sign up";

    public SignUpFragment() {
        // Required empty public constructor
    }

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }

    @Override
    public String getFragmentTitle() {
        return SignUpFragment.fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        SignUpFragment.fragmentTitle = fragmentTitle;
    }
}