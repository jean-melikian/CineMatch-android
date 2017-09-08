package com.cinematching.application.webservice;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public interface IServiceResultListener<T> {
    void onResult(ServiceResult<T> result);
}
