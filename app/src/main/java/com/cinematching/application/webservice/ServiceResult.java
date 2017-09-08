package com.cinematching.application.webservice;

import com.cinematching.application.webservice.errors.ServiceException;

/**
 * Created by Jean-Christophe Melikian on 06/09/2017.
 */

public class ServiceResult<T> {
    private T data;
    private ServiceException error;


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ServiceException getError() {
        return error;
    }

    public void setError(ServiceException error) {
        this.error = error;
    }
}
