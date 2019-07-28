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

public class RecyclerAdapterCartItems extends RecyclerView.Adapter<RecyclerAdapterCartItems.ViewHolder> {

    private Context context;
    List<CartItems> cartItemsList = new ArrayList<>();

    public RecyclerAdapterCartItems(Context context, List<CartItems> cartItemsList) {
        this.context = context;
        this.cartItemsList = cartItemsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(cartItemsList.get(position).getP_name());
        holder.textView1.setText(cartItemsList.get(position).getP_price());
        Picasso.get().load(cartItemsList.get(position).getImg()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return cartItemsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView,textView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.itemimg);
            textView = itemView.findViewById(R.id.itemname);
            textView1 = itemView.findViewById(R.id.itemprice);
        }
    }
}
