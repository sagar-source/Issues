package com.example.githubissues;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

   public static String BASE_URL = "https://api.github.com/repos/firebase/firebase-ios-sdk/";

    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;

    }
}
