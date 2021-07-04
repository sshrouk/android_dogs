package com.example.project2;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PostsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPosts(List<Posts> posts);

    @Query("DELETE FROM POSTS_TABLE")
    Completable deleteAll();

    @Query("SELECT *FROM POSTS_TABLE")
    Single<List<Posts>> getAllPosts();

    @Query("SELECT *FROM POSTS_TABLE WHERE ID=:id")
    Single<Posts> getById(int id);

}

