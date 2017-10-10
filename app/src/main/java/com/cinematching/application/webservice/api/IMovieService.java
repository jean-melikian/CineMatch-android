package com.cinematching.application.webservice.api;

import com.cinematching.application.models.Movie;
import com.cinematching.application.webservice.IServiceResultListener;

import java.util.List;

/**
 * Created by meryl on 08/09/2017.
 */

public interface IMovieService {
    void read(String id, IServiceResultListener<Movie> resultListener);
    void getScreening(IServiceResultListener<List<Movie>> resultListener);
    void getUpcomming(IServiceResultListener<List<Movie>> resultListener);
}
