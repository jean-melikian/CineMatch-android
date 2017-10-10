package com.cinematching.application.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cinematching.application.R;
import com.cinematching.application.controllers.fragments.BaseFragment;
import com.cinematching.application.controllers.fragments.login.SignInFragment;
import com.cinematching.application.controllers.fragments.login.SignUpFragment;
import com.cinematching.application.webservice.ServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    private static final int TABS_NUMBER = 2;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    @BindView(R.id.container)
    public ViewPager mViewPager;

    @BindView(R.id.tabs)
    public TabLayout tabLayout;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ServiceGenerator.hasAuthorization()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.clearOnPageChangeListeners();
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(mViewPager);
    }

    public void validateAuthentication() {
        String authToken = getSharedPreferences("authorization", Context.MODE_PRIVATE).getString("authToken", null);
        if (authToken != null && !authToken.isEmpty()) {
            ServiceGenerator.injectToken(authToken);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return SignInFragment.newInstance();
                case 1:
                    return SignUpFragment.newInstance();
                default:
                    return SignInFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return TABS_NUMBER;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return ((BaseFragment) getItem(position)).getFragmentTitle();
        }
    }
}
