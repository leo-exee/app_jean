package com.example.sensmove.dao;
import com.example.sensmove.entities.User;
import com.example.sensmove.entities.Value;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("/app-api/login?")
    Call<User> performLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/app-api/get?")
    Call<List<Value>> getDevices(@Field("email") String email, @Field("password") String password);
}