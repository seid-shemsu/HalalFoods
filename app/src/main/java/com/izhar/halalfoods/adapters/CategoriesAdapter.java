package com.izhar.halalfoods.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.izhar.halalfoods.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> {
    Context context;
    List<String> images, categories;

    public CategoriesAdapter(Context context, List<String> images, List<String> categories) {
        this.context = context;
        this.images = images;
        this.categories = categories;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categories, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.cat.setText(categories.get(position));
        Picasso.with(context).load(images.get(position)).placeholder(R.drawable.food).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView cat;
        public Holder(@NonNull View itemView) {
            super(itemView);
            cat = itemView.findViewById(R.id.category);
            image = itemView.findViewById(R.id.image);
        }
    }
}
