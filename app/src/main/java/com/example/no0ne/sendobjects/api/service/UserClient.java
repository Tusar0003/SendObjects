package com.example.no0ne.sendobjects.api.service;

import com.example.no0ne.sendobjects.api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by no0ne on 1/18/18.
 */

public interface UserClient {

    @POST("user")
    Call<User> createAccount(@Body User user);
}
