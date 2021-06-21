package com.izhar.halalfoods.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.izhar.halalfoods.R;
import com.izhar.halalfoods.models.Food;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.Holder> {
    Context context;
    List<Food> foods;
    TextView fab_number;
    public int items = 0;

    public FoodListAdapter(Context context, List<Food> foods, TextView fab_number) {
        this.context = context;
        this.foods = foods;
        this.fab_number = fab_number;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_food, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Food food = foods.get(position);
        holder.name.setText(food.getName());
        holder.price.setText(food.getPrice() + "ETB");
        holder.availability.setText(food.getAvailability());
        if (!food.getAvailability().equalsIgnoreCase("available")){
            holder.availability.setPaintFlags(holder.availability.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.fab.setEnabled(false);
        }
        File img = context.getApplicationContext().getFileStreamPath("food" + food.getId());
        if (img.exists()){
            holder.image.setImageBitmap(loadImage(context, "food" + food.getId()));
        }
        else {
            Picasso.with(context).load(food.getUrl()).placeholder(R.drawable.food).into(holder.image);
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Looper.prepare();
                            saveImage(context, Picasso.with(context).load(food.getUrl()).get(), "food" + food.getId());
                        } catch (IOException e) {
                            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }).start();
            } catch (Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        holder.fab.setTag("removed");
        holder.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.fab.getTag().equals("added")){
                    holder.fab.setTag("removed");
                    holder.fab.setImageDrawable(context.getResources().getDrawable(R.drawable.add));
                    items--;
                }
                else {
                    holder.fab.setTag("added");
                    holder.fab.setImageDrawable(context.getResources().getDrawable(R.drawable.cancel));
                    items++;
                }

                fab_number.setText(items + "");
            }
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price, availability;
        FloatingActionButton fab;
        public Holder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            availability = itemView.findViewById(R.id.availability);
            fab = itemView.findViewById(R.id.fab);
            fab.setTag("removed");
            /*fab.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("UseCompatLoadingForDrawables")
                @Override
                public void onClick(View v) {

                    *//*if (order.getText().toString().equalsIgnoreCase("order")) {
                        toCart.add(getAdapterPosition(), new Food(foods.get(getAdapterPosition()).getName(), foods.get(getAdapterPosition()).getPrice()));
                        order.setText("Cancel");
                        order.setBackgroundResource(R.drawable.btn_remove);
                        order.setBackgroundColor(context.getResources().getColor(R.color.red));
                        items++;
                    } else {
                        order.setText("Order");
                        order.setBackgroundResource(R.drawable.btn_add);
                        order.setBackgroundColor(context.getResources().getColor(R.color.purple_500));
                        items--;
                    }*//*
                    fab_number.setText(items + "");
                }
            });*/
        }
    }


    private Bitmap loadImage(Context context, String imageName) {
        Bitmap bitmap = null;
        FileInputStream fiStream;
        try {
            fiStream    = context.openFileInput(imageName);
            bitmap      = BitmapFactory.decodeStream(fiStream);
            fiStream.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return bitmap;
    }

    private void saveImage(Context context, Bitmap b, String imageName) {
        FileOutputStream foStream;
        try {
            foStream = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            b.compress(Bitmap.CompressFormat.JPEG, 80, foStream);
            foStream.close();
        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
