package com.hamidul.demoecommerceapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.PixelCopy;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.databinding.ActivityProductDetailBinding;
import com.hamidul.demoecommerceapp.model.Product;
import com.hamidul.demoecommerceapp.utils.Constants;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;

    Product currentProduct;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("image");
        int id = getIntent().getIntExtra("id",0);
        double price = getIntent().getDoubleExtra("price",0);

        Glide.with(this)
                .load(image)
                .into(binding.productImage);

        getProductDetails(id);

        getSupportActionBar().setTitle(name);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Cart cart = TinyCartHelper.getCart();

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cart.addItem(currentProduct,1);

                setToast("Added to cart");

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cart){
            startActivity(new Intent(this,CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void getProductDetails(int mId){

        RequestQueue requestQueue = Volley.newRequestQueue(ProductDetailActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constants.GET_PRODUCTS_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int id = jsonObject.getInt("id");

                        if (id==mId){
                            String description = jsonObject.optString("description");
                            binding.productDescription.setText(Html.fromHtml(description));

                            currentProduct = new Product(
                                    jsonObject.getString("name"),
                                    jsonObject.getString("image_url"),
                                    jsonObject.getString("status"),
                                    jsonObject.getDouble("price"),
                                    jsonObject.getDouble("discount"),
                                    jsonObject.getInt("stock"),
                                    jsonObject.getInt("id")
                            );

                            break;

                        }
                        else {
                            binding.productDescription.setText("Something Wrong Please Contact This Owner");
                        }


                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductDetailActivity.this, "Server Error "+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);


    }

    void getProductDetailsWithDummyJSON(String sName){

        String url = "https://dummyjson.com/products";

        RequestQueue requestQueue = Volley.newRequestQueue(ProductDetailActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("products");

                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.optString("title");
                        if (name.equals(sName)){
                            String description = jsonObject.optString("description");
                            binding.productDescription.setText(Html.fromHtml(description));
                            break;
                        }
                        else {
                            binding.productDescription.setText("Something Wrong Please Contact This Owner");
                        }


                    }


                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductDetailActivity.this, "Server Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void setToast (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(ProductDetailActivity.this,message,Toast.LENGTH_LONG);
        toast.show();
    }


}