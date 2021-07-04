package com.example.project2;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends AndroidViewModel {
    final private PostsDatabase postsDatabase = PostsDatabase.getInstance(getApplication());

    MutableLiveData<Posts> post = new MutableLiveData<>();

    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }


    public void getPosts(int id) {
        postsDatabase.getPostsDao()
                .getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Posts>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull Posts posts) {
                        Log.d("Api", "" + posts);
                        post.postValue(posts); //MutableLiveData
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        Log.d("Api", "" + e.getMessage());
                    }
                });
    }

}

