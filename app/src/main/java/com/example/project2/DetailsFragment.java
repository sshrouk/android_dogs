package com.example.project2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class DetailsFragment extends Fragment {
    int id;
    DetailsViewModel detailViewModel;
    TextView tvId, tvName, tvTemp, tvOrgin, tvLife_spam, tvBreed_group, tvBred_for, tvCountry_code;
    ImageView imgURL;
    Button share_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvId = view.findViewById(R.id.id_details);
        tvName = view.findViewById(R.id.name_details);
        imgURL = view.findViewById(R.id.bg_image);
        tvTemp = view.findViewById(R.id.temp);
        tvOrgin = view.findViewById(R.id.orgin);
        tvLife_spam = view.findViewById(R.id.life_spam);
        tvBreed_group = view.findViewById(R.id.breed_group);
        tvBred_for = view.findViewById(R.id.bred_for);
        tvCountry_code = view.findViewById(R.id.country_code);
        share_btn = view.findViewById(R.id.share);
        id = DetailsFragmentArgs.fromBundle(getArguments()).getId();
        Log.d("Id", "" + id);
        detailViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailViewModel.getPosts(id);
        detailViewModel.post.observe(getViewLifecycleOwner(), new Observer<Posts>() {
            @Override
            public void onChanged(Posts posts) {
                Log.d("Api", " " + posts.getName());
                Log.d("Api", " " + posts.getUrl());
                Log.d("Api", " " + posts.getBred_for());
                Log.d("Api", " " + posts.getBreed_group());
                Log.d("Api", " " + posts.getLife_span());
                Log.d("Api", " " + posts.getTemperament());
                Log.d("Api", " " + posts.getId());
                Log.d("Api", " " + posts.getOrigin());
                Log.d("Api", " " + posts.getCountry_code());
                tvBred_for.setText(posts.getBred_for());
                tvBreed_group.setText(posts.getBreed_group());
                tvId.setText(String.valueOf(posts.getId()));
                tvLife_spam.setText(posts.getLife_span());
                tvName.setText(posts.getName());
                tvOrgin.setText(posts.getOrigin());
                tvTemp.setText(posts.getTemperament());
                tvCountry_code.setText(posts.getCountry_code());
                Glide.with(imgURL.getContext())
                        .load(posts.getUrl())
                        .into(imgURL);
                share_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out your INFO");
                        if (posts.getCountry_code() != null ||
                                posts.getOrigin() != null ||
                                posts.getTemperament() != null ||
                                posts.getLife_span() != null ||
                                posts.getBreed_group() != null ||
                                posts.getBred_for() != null ||
                                posts.getName() != null) {
                            shareIntent.putExtra(Intent.EXTRA_TEXT, posts.getCountry_code() + "\n" +
                                    posts.getOrigin() + "\n" + posts.getId() + "\n" + posts.getTemperament()
                                    + "\n" + posts.getLife_span() + "\n" + posts.getBreed_group() + "\n" + posts.getBred_for()
                                    + "\n" + posts.getName());
                        }
                        startActivity(Intent.createChooser(shareIntent, "Share With"));

                    }
                });

            }
        });

    }
}