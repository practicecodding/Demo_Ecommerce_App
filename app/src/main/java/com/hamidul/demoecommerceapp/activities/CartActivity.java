package com.hamidul.demoecommerceapp.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}