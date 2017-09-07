package com.cinematching.application.controllers.fragments.movie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cinematching.application.R;

/**
 * Created by meryl on 07/09/2017.
 */

public class MovieInfoFragment extends Fragment {
    ImageView image;
    TextView title;
    TextView date;
    TextView genres;
    TextView synopsis;
    Button addWatch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_infos, container, false);

        image = (ImageView) view.findViewById(R.id.movieInfo_image);
        title = (TextView) view.findViewById(R.id.movieInfo_title);
        date = (TextView) view.findViewById(R.id.movieInfo_date);
        genres = (TextView) view.findViewById(R.id.movieInfo_genres);
        synopsis = (TextView) view.findViewById(R.id.movieInfo_synopsisInput);
        addWatch = (Button) view.findViewById(R.id.movieInfo_addButton);

        initView();
        return view;
    }

    public MovieInfoFragment(){

    }

    private void initView(){
    }
}
