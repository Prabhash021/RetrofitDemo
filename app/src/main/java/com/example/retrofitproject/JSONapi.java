package com.example.retrofitproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONapi {
    @GET("posts")
    Call<List<PostModel>> getPosts();
}
