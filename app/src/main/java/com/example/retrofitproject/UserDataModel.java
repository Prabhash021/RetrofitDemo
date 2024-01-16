package com.example.retrofitproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
@Generated("jsonschema2pojo")
public class UserDataModel {
    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String job;

    public UserDataModel(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}