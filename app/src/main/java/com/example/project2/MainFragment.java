package com.example.project2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {
    //Button button;
    MainViewModel mainViewModel;
    List<Posts> postsList = new ArrayList<>();
    RecyclerView rvPosts;
    PostsAdapter postAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        postAdapter = new PostsAdapter(null);
        rvPosts = view.findViewById(R.id.rvPosts);
        rvPosts.setAdapter(postAdapter);
        rvPosts.setVisibility(View.GONE);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getPosts();
        mainViewModel.getAllPostsLocally();
        mainViewModel.listMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {

                postsList.addAll(posts);
                Log.d("Api", " " + postsList.size());
                postAdapter.setPosts(posts);
                postAdapter.notifyDataSetChanged();
                rvPosts.setAdapter(postAdapter);
                rvPosts.setVisibility(View.VISIBLE);

            }
        });
    }
}