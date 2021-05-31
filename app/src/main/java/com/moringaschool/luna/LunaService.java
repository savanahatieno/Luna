package com.moringaschool.luna;

import com.moringaschool.luna.Model.Project;

import java.util.List;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LunaService {
    @GET
    Observable<List<Project>> getProject(@Header("Authorization") String token);
}
