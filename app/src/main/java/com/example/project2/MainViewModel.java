package com.example.project2;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    MutableLiveData<List<Posts>> listMutableLiveData = new MutableLiveData<>();
    final PostsDatabase postsDatabase = PostsDatabase.getInstance(getApplication());

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void getPosts() {
        ApiClient.getInstance().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Posts>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Posts> posts) {
                        Log.d("Api", "" + posts.size());
                        storeLocally(posts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Api", "" + e.getMessage());
                    }
                });

    }

    public void storeLocally(List<Posts> posts) {

        postsDatabase.getPostsDao().insertPosts(posts)
                .subscribeOn(Schedulers.computation())
                .subscribe(
                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("Api", "sub");
                            }

                            @Override
                            public void onComplete() {
                                Log.d("Api", "suc");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Api", e.getMessage());
                            }
                        }
                );
    }

    public void getAllPostsLocally() {

        postsDatabase.getPostsDao()
                .getAllPosts()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Posts>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Posts> posts) {
                        Log.d("Api2", "" + posts.size());
                        listMutableLiveData.postValue(posts);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Api1", "" + e.getMessage());
                    }
                });
    }

}


