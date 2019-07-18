package com.example.volleyhorizontalrecyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterTopDeals extends RecyclerView.Adapter<RecyclerAdapterTopDeals.ViewHolder> {
    Context context;
    List<TopDeals> topDealsList = new ArrayList<>();

    public RecyclerAdapterTopDeals(Context context, List<TopDeals> topDealsList) {
        this.context = context;
        this.topDealsList = topDealsList;
    }

    @NonNull
    @Override
    public RecyclerAdapterTopDeals.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterTopDeals.ViewHolder holder, int position) {
        holder.textView.setText(topDealsList.get(position).getMedicinetitle());
        //Picasso.get().load(topDealsList.get(position).getMedicineimg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return topDealsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
            imageView=itemView.findViewById(R.id.pic);
        }
    }
}