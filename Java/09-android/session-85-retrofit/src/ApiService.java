// ApiService.java — Retrofit API interface
// نیازمند Android SDK
package com.example.myapp;

import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface ApiService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUser(@Path("id") int id);

    @POST("users")
    Call<User> createUser(@Body User user);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

    @GET("posts")
    Call<List<Post>> getPosts();
}
