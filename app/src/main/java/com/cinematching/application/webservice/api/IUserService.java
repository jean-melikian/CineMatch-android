package com.cinematching.application.webservice.api;

import com.cinematching.application.models.Authorization;
import com.cinematching.application.models.Credentials;
import com.cinematching.application.models.User;
import com.cinematching.application.webservice.IServiceResultListener;

import java.util.List;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public interface IUserService {

    void authenticate(Credentials credentials, IServiceResultListener<Authorization> resultListener);

    void create(User user, IServiceResultListener<String> resultListener);

    void read(IServiceResultListener<List<User>> resultListener);

    void readById(String id, IServiceResultListener<User> resultListener);
}
