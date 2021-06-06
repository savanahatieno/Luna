package com.moringaschool.luna;


import com.moringaschool.luna.ApiUser.projects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoistApi {

    @GET("projects")
    static Call<List<projects>> getprojects() {
        return null;
    }
}
