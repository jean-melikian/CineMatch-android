package com.cinematching.application.webservice.api;

import com.cinematching.application.models.User;
import com.cinematching.application.webservice.IServiceResultListener;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public interface IUserService {

    void create(User user, IServiceResultListener<String> resultListener);

    void read(String id, IServiceResultListener<String> resultListener);
}
