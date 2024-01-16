package com.example.retrofitproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReteroRequestApi {
    @POST("users")
    Call<UserDataModel> createPost(@Body UserDataModel userDataModel);
}
