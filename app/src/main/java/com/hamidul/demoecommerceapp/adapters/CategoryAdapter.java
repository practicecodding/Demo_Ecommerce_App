package com.hamidul.demoecommerceapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.activities.ProductDetailActivity;
import com.hamidul.demoecommerceapp.databinding.ItemCategoriesBinding;
import com.hamidul.demoecommerceapp.model.Category;

import java.util.ArrayList;
import java.util.HashMap;

import kotlin.CharCodeJVMKt;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context context;
    ArrayList<HashMap<String,String>> categories;
    HashMap<String,String> hashMap;
    Toast toast;

    public CategoryAdapter(Context context, ArrayList<HashMap<String,String>> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_categories,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        hashMap = categories.get(position);
        String id = hashMap.get("id");
        String name = hashMap.get("name");
        String color = hashMap.get("color");
        String imageUrl = hashMap.get("imageUrl");

        holder.binding.label.setText(Html.fromHtml(name));
        Glide.with(context)
                .load(imageUrl)
                .into(holder.binding.image);
        holder.binding.image.setBackgroundColor(Color.parseColor("#"+color));


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        ItemCategoriesBinding binding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = ItemCategoriesBinding.bind(itemView);

        }
    }


    private void setToast (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }

}
