package com.cinematching.application.controllers.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinematching.application.R;
import com.cinematching.application.controllers.fragments.BaseFragment;

/**
 * Created by meryl on 07/09/2017.
 */

public class MovieInfoFragment extends BaseFragment {
    private static String fragmentTitle = "Movie details";
    ImageView image;
    TextView title;
    TextView date;
    TextView genres;
    TextView synopsis;
    Button addWatch;

    public MovieInfoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_info, container, false);

        image = (ImageView) view.findViewById(R.id.movieInfo_image);
        title = (TextView) view.findViewById(R.id.movieInfo_title);
        date = (TextView) view.findViewById(R.id.movieInfo_date);
        genres = (TextView) view.findViewById(R.id.movieInfo_genres);
        synopsis = (TextView) view.findViewById(R.id.movieInfo_synopsisInput);
        addWatch = (Button) view.findViewById(R.id.movieInfo_addButton);

        initView();
        return view;
    }

    private void initView() {
    }

    @Override
    public String getFragmentTitle() {
        return fragmentTitle;
    }

    @Override
    public void setFragmentTitle(String fragmentTitle) {
        MovieInfoFragment.fragmentTitle = fragmentTitle;
    }
}
