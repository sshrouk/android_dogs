package com.example.project2;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostsInterface {

    @GET("DevTides/DogsApi/master/dogs.json")
    Single<List<Posts>> getPosts();

    @POST("DevTides/DogsApi/master/dogs.json")
    Single<Posts> storePosts(@Body Posts posts);
}
