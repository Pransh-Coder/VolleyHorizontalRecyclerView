package com.example.volleyhorizontalrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.volleyhorizontalrecyclerview.Category;
import com.example.volleyhorizontalrecyclerview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList = new ArrayList<>();

    public RecyclerAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(categoryList.get(position).getCategorynames());
        //Picasso.get().load(categoryList.get(position).getCategorynames()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        CircleImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.category);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}

