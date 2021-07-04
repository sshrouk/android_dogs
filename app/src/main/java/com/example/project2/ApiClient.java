package com.example.project2;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private final static String BASEURL = "https://raw.githubusercontent.com/";
    private PostsInterface postInterface;
    private static ApiClient Instance;

    public ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        postInterface = retrofit.create(PostsInterface.class);

    }

    public Single<List<Posts>> getPosts() {
        return postInterface.getPosts();
    }

    public static ApiClient getInstance() {
        if (Instance == null) {
            Instance = new ApiClient();
        }
        return Instance;
    }
}

