package com.example.sensmove.instance;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jean.leopold-jacquet.com/";
    public static Retrofit getRetrofitInstance() {

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}