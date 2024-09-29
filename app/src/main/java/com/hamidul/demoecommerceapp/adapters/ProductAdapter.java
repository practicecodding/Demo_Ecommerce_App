package com.hamidul.demoecommerceapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.activities.ProductDetailActivity;
import com.hamidul.demoecommerceapp.databinding.ItemProductBinding;
import com.hamidul.demoecommerceapp.model.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    Context context;
    ArrayList<Product> products;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    Toast toast;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product = products.get(position);

        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);
        holder.binding.label.setText(product.getName());
        holder.binding.price.setText(String.format("BDT %.2f",product.getPrice()));
//        holder.binding.price.setText("BDT : "+decimalFormat.format(product.getPrice())+" TK");
//        holder.binding.price.setText(String.format("BDT %.2f",product.getPrice())+" TK");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ProductDetailActivity.class);
                intent.putExtra("name",product.getName());
                intent.putExtra("image",product.getImage());
                intent.putExtra("id",product.getId());
                intent.putExtra("price",product.getPrice());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ItemProductBinding binding;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemProductBinding.bind(itemView);
        }
    }

    private void setToast (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }

}
