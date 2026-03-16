package com.example.studentloginsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class shoppingAdapter extends RecyclerView.Adapter<shoppingAdapter.ShoppingViewHolder> {

    private List<Product> productList;
    private CartItemListener listener;

    public shoppingAdapter(List<Product> productList, CartItemListener listener) {
        this.productList = productList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public shoppingAdapter.ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart_card, parent, false);
        return new ShoppingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull shoppingAdapter.ShoppingViewHolder holder, int position) {
        Product result = productList.get(position);

        holder.img1.setImageResource(result.getImageURL());

        holder.name1.setText(result.getName());

        holder.price1.setText("₱" + String.valueOf(result.getPrice()));

        holder.result1.setText(String.valueOf(result.getQuantity()));

        holder.btnAdd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.AddOnClick(result, position);
                }
            }
        });

        holder.btnMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.MinusOnClick(result, position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public static class ShoppingViewHolder extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView name1, price1, result1;
        Button btnAdd1, btnMinus1;

        public ShoppingViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.productImage1);

            name1 = itemView.findViewById(R.id.productTitle1);

            price1 = itemView.findViewById(R.id.productPrice1);

            result1 = itemView.findViewById(R.id.productResult1);

            btnAdd1 = itemView.findViewById(R.id.btnAdd1);

            btnMinus1 = itemView.findViewById(R.id.btnMinus1);
        }
    }
}
