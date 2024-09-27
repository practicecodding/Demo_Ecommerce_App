package com.hamidul.demoecommerceapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    }
}