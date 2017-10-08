package com.cinematching.application.webservice.api;

import com.cinematching.application.models.Movie;
import com.cinematching.application.webservice.IServiceResultListener;

/**
 * Created by meryl on 08/09/2017.
 */

public interface IMovieService {
    void read(String id, IServiceResultListener<String> resultListener);
    void getScreening(IServiceResultListener<String> resultListener);
    void getUpcomming(IServiceResultListener<String> resultListener);
}
