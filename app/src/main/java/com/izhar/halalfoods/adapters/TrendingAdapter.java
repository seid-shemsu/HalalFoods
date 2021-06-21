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
import java.util.Map;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.Holder> {
    Context context;
    List<String> images, descriptions;

    public TrendingAdapter(Context context, List<String> images, List<String> descriptions) {
        this.context = context;
        this.images = images;
        this.descriptions = descriptions;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_dished, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.desc.setText(descriptions.get(position));
        Picasso.with(context).load(images.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView desc;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            desc = itemView.findViewById(R.id.desc);

        }
    }
}
