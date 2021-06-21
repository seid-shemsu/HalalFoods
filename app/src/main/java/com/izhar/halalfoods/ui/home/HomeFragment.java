package com.izhar.halalfoods.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.izhar.halalfoods.R;
import com.izhar.halalfoods.adapters.CategoriesAdapter;
import com.izhar.halalfoods.adapters.TrendingAdapter;
import com.izhar.halalfoods.models.Food;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    /*RecyclerView recyclerView;
    FoodListAdapter fla;
    List<Food> food_list;
    DatabaseReference foods;
    TextView empty;
    ProgressBar progressBar;
    FloatingActionButton fab;
    TextView fab_number;*/
    RecyclerView trending, category;
    CategoriesAdapter cat_adapter;
    TrendingAdapter trend_adapter;
    List<String> cat_images, descs, trend_images, cats;
    TextView trend_empty, cat_empty;
    ProgressBar trend_progress, cat_progress;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        trend_empty = root.findViewById(R.id.trend_empty);
        cat_empty = root.findViewById(R.id.cat_empty);
        trend_progress = root.findViewById(R.id.trend_progress);
        cat_progress = root.findViewById(R.id.cat_progress);

        category = root.findViewById(R.id.cat_recycler);
        category.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        category.setHasFixedSize(true);

        trending = root.findViewById(R.id.trend_recycler);
        trending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        trending.setHasFixedSize(true);

        cat_images = new ArrayList<>();
        descs = new ArrayList<>();
        trend_images = new ArrayList<>();
        cats = new ArrayList<>();
        //trending population
        DatabaseReference t = FirebaseDatabase.getInstance().getReference("trending");
        t.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                trend_images.clear();
                descs.clear();
                if (snapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        trend_images.add(dataSnapshot.child("image").toString());
                        descs.add(dataSnapshot.child("desc").toString());
                    }
                    trend_adapter = new TrendingAdapter(getContext(), trend_images, descs);
                    trending.setAdapter(trend_adapter);
                }
                else
                    trend_empty.setVisibility(View.VISIBLE);
                trend_progress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //category population

        DatabaseReference c = FirebaseDatabase.getInstance().getReference("category");
        c.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cat_images.clear();
                cats.clear();
                if (snapshot.hasChildren()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        cat_images.add(dataSnapshot.child("image").toString());
                        cats.add(dataSnapshot.child("category").toString());
                    }
                    cat_adapter = new CategoriesAdapter(getContext(), cat_images, cats);
                    category.setAdapter(cat_adapter);
                }
                else
                    cat_empty.setVisibility(View.VISIBLE);
                cat_progress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*fab = root.findViewById(R.id.fab);
        recyclerView = root.findViewById(R.id.recycler);
        progressBar = root.findViewById(R.id.progress);
        empty = root.findViewById(R.id.empty);
        fab_number = root.findViewById(R.id.fab_number);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);
        food_list = new ArrayList<>();
        foods = FirebaseDatabase.getInstance().getReference().child("foods");
        foods.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                food_list.clear();
                if (snapshot.hasChildren()){
                    empty.setVisibility(View.GONE);
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        food_list.add(dataSnapshot.getValue(Food.class));
                    }
                    fla = new FoodListAdapter(getContext(), food_list, fab_number);
                    recyclerView.setAdapter(fla);
                }
                else
                    empty.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return root;
    }
}