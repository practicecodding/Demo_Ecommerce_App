package com.hamidul.demoecommerceapp.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.adapters.CartAdapter;
import com.hamidul.demoecommerceapp.databinding.ActivityCartBinding;
import com.hamidul.demoecommerceapp.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    CartAdapter cartAdapter;
    ArrayList<Product> products = new ArrayList<>();
    Toast toast;
    Cart cart;
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.cartList);

        products = new ArrayList<>();

        cart = TinyCartHelper.getCart();

        for (Map.Entry<Item, Integer> item: cart.getAllItemsWithQty().entrySet()){

            Product product = (Product) item.getKey();
            int quantity = item.getValue();

            product.setQuantity(quantity);

            products.add(product);

        }

        cartAdapter = new CartAdapter(CartActivity.this, products, new CartAdapter.CartListener() {
            @Override
            public void onQuantityChanged() {
                binding.subtotal.setText(String.format("BDT %.2f",cart.getTotalPrice()));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,layoutManager.getOrientation());
        binding.cartList.setLayoutManager(layoutManager);
        binding.cartList.addItemDecoration(itemDecoration);
        binding.cartList.setAdapter(cartAdapter);
        cartAdapter.setOnItemClickListener(new CartAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Product product = products.get(position);
                cart.removeItem(product);
                binding.subtotal.setText(String.format("BDT %.2f",cart.getTotalPrice()));

                products.remove(position);
                cartAdapter.notifyItemRemoved(position);
            }
        });

        binding.subtotal.setText(String.format("BDT %.2f",cart.getTotalPrice()));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            Product product = products.get(viewHolder.getAdapterPosition());
            cart.removeItem(product);
            binding.subtotal.setText(String.format("BDT %.2f",cart.getTotalPrice()));

            products.remove(viewHolder.getAdapterPosition());
            cartAdapter.notifyDataSetChanged();

        }
    };


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setToast (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(CartActivity.this,message,Toast.LENGTH_LONG);
        toast.show();
    }



}