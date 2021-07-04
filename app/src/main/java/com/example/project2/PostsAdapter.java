package com.example.project2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {
    private List<Posts> posts;

    public PostsAdapter(List<Posts> posts) {
        this.posts = posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.name.setText(posts.get(position).getName());
        Glide.with(holder.itemView.getContext()).load(posts.get(position).getUrl()).into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragmentDirections.ActionMainFragmentToDetailsFragment2 mainFragmentDirections =
                        MainFragmentDirections.actionMainFragmentToDetailsFragment2();
                mainFragmentDirections.setId(posts.get(position).getId());
                Navigation.findNavController(view).navigate(mainFragmentDirections);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (posts != null) {
            return posts.size();
        }
        return 0;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
        }
    }
}


