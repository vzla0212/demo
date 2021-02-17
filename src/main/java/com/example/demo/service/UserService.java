package com.example.demo.service;

import com.example.demo.domain.UserG;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UserService {

    @GET("/users")
    public Call<List<UserG>> getUsers(
            @Query("per_page") int per_page,
            @Query("page") int page);

    @GET("/users/{username}")
    public Call<UserG> getUser(@Path("username") String username);
}
