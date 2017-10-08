package com.cinematching.application.controllers.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.cinematching.application.R;
import com.cinematching.application.controllers.fragments.BaseFragment;

/**
 * Created by meryl on 07/09/2017.
 */

public class UserParamsFragment extends BaseFragment {

    private static String fragmentTitle = "Edit profile";
    ImageView image;
    Button uploadImage;
    Button password;
    EditText nickname;
    EditText lastname;
    EditText firstname;
    EditText birthdate;
    EditText zipcode;
    EditText city;
    EditText email;
    Button back;
    Button save;

    public UserParamsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_params, container, false);
        setFragmentTitle(fragmentTitle);

        image = (ImageView) view.findViewById(R.id.userParams_image);
        uploadImage = (Button) view.findViewById(R.id.userParams_uploadImage);
        password = (Button) view.findViewById(R.id.userParams_password);
        nickname = (EditText) view.findViewById(R.id.userParams_nicknameInput);
        lastname = (EditText) view.findViewById(R.id.userParams_lastnameInput);
        firstname = (EditText) view.findViewById(R.id.userParams_firstnameInput);
        birthdate = (EditText) view.findViewById(R.id.userParams_birthdateInput);
        zipcode = (EditText) view.findViewById(R.id.userParams_zipcodeInput);
        city = (EditText) view.findViewById(R.id.userParams_cityInput);
        email = (EditText) view.findViewById(R.id.userParams_emailInput);
        back = (Button) view.findViewById(R.id.userParams_backButton);
        save = (Button) view.findViewById(R.id.userParams_saveButton);

        return view;
    }

    @Override
    public String getFragmentTitle() {
        return fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        UserParamsFragment.fragmentTitle = fragmentTitle;
    }


}
