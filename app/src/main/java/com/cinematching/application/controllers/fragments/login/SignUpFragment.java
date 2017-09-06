package com.cinematching.application.controllers.fragments.login;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.cinematching.application.R;
import com.cinematching.application.controllers.fragments.BaseFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends BaseFragment implements SignUpController {

    private static String fragmentTitle = "Sign up";
    private Calendar birthdate;

    @BindView(R.id.field_birthdate)
    EditText fieldBirthdate;

    private DatePickerDialog.OnDateSetListener dateSetListener;

    private Unbinder unbinder;
    private static final int APP_MINIMUM_AGE = 18;

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
        unbinder = ButterKnife.bind(this, view);

        birthdate = Calendar.getInstance();
        birthdate.set(Calendar.YEAR, birthdate.get(Calendar.YEAR) - APP_MINIMUM_AGE);

        dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePickerView, int year, int monthOfYear, int dayOfMonth) {
                birthdate.set(Calendar.YEAR, year);
                birthdate.set(Calendar.MONTH, monthOfYear);
                birthdate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateBirthdateField();
            }
        };
        fieldBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), dateSetListener, birthdate.get(Calendar.YEAR), birthdate.get(Calendar.MONTH),
                        birthdate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void updateBirthdateField() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);

        fieldBirthdate.setText(sdf.format(birthdate.getTime()));
    }

    @Override
    public String getFragmentTitle() {
        return SignUpFragment.fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        SignUpFragment.fragmentTitle = fragmentTitle;
    }

    @Override
    public void register() {

    }
}