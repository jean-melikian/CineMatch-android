package com.cinematching.application.controllers.fragments.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cinematching.application.R;
import com.cinematching.application.adapters.UsersListAdapter;
import com.cinematching.application.controllers.fragments.BaseFragment;
import com.cinematching.application.models.User;
import com.cinematching.application.webservice.IServiceResultListener;
import com.cinematching.application.webservice.ServiceResult;
import com.cinematching.application.webservice.api.retrofit.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentUsersFragment extends BaseFragment {

    private static String fragmentTitle = "Recent contacts";
    UserService userService;
    private RecyclerView usersRecyclerView;

    public RecentUsersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_users, container, false);

        setFragmentTitle(fragmentTitle);

        usersRecyclerView = (RecyclerView) view.findViewById(R.id.usersList_recyclerView);

        userService = new UserService();

        UsersListAdapter usersListAdapter = new UsersListAdapter(getActivity(), null);

        usersRecyclerView.setAdapter(usersListAdapter);

        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        userService.read(new IServiceResultListener<List<User>>() {
            @Override
            public void onResult(ServiceResult<List<User>> result) {
                usersListAdapter.setUsers(result.getData());
            }
        });

        return view;
    }

    @Override
    public String getFragmentTitle() {
        return RecentUsersFragment.fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        RecentUsersFragment.fragmentTitle = fragmentTitle;
    }
}
