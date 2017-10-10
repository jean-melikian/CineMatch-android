package com.cinematching.application.controllers.fragments.login;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cinematching.application.R;
import com.cinematching.application.SessionData;
import com.cinematching.application.controllers.MainActivity;
import com.cinematching.application.controllers.fragments.BaseFragment;
import com.cinematching.application.models.Authorization;
import com.cinematching.application.models.Credentials;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.ServiceResult;
import com.cinematching.application.webservice.api.retrofit.UserService;
import com.cinematching.application.webservice.errors.ServiceExceptionType;

import org.joda.time.DateTime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link #Fragment} subclass.
 */
public class SignInFragment extends BaseFragment implements SignInController {

    private static String fragmentTitle = "Sign in";
    @BindView(R.id.btn_email_sign_in)
    Button buttonEmailSignIn;
    @BindView(R.id.field_email)
    EditText emailField;
    @BindView(R.id.field_password)
    EditText passwordField;

    private Unbinder unbinder;
    private SharedPreferences sp;
    private UserService userService;
    private Credentials credentials;
    private AlertDialog dialog;

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
        sp = getActivity().getSharedPreferences("authorization", Context.MODE_PRIVATE);

        userService = new UserService();
        credentials = new Credentials();

        initAlertDialog();

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
        credentials.setUsername(emailField.getText().toString());
        credentials.setPassword(passwordField.getText().toString());
        authenticate();
    }

    private void initAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Login failed")
                .setMessage("Wrong username or password !")
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
    }

    @Override
    public void authenticate() {
        if (credentials.validateInput()) {
            userService.authenticate(credentials, new IServiceResultListener<Authorization>() {
                @Override
                public void onResult(ServiceResult<Authorization> result) {
                    if (result.getData() != null) {
                        SessionData.get().setToken(result.getData().getToken());
                        SessionData.get().setExpiration(DateTime.now().plusSeconds(result.getData().getTokenValidityInSeconds()));


                        boolean isAuth = SessionData.get().validateAuthentication();
                        if (isAuth) {
                            startActivity(new Intent(getActivity(), MainActivity.class));
                        }
                    } else if (result.getError() != null
                            && result.getError().getType() == ServiceExceptionType.BAD_REQUEST) {
                        if (!dialog.isShowing()) {
                            dialog.show();
                        }
                    }
                }
            });

        } else {
            Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
