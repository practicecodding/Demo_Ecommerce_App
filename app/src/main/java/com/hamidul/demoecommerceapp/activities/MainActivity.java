package com.hamidul.demoecommerceapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.adapters.CategoryAdapter;
import com.hamidul.demoecommerceapp.adapters.ProductAdapter;
import com.hamidul.demoecommerceapp.databinding.ActivityMainBinding;
import com.hamidul.demoecommerceapp.model.Category;
import com.hamidul.demoecommerceapp.model.Product;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategories();
        initProducts();
        initCarousel();

    }

    private void initCarousel() {
        binding.carousel.addData(new CarouselItem("https://png.pngtree.com/png-vector/20220124/ourmid/pngtree-offer-text-vector-file-png-image_4360383.png","Carousel Three"));
        binding.carousel.addData(new CarouselItem("https://img.lovepik.com/element/45010/2119.png_860.png","Carousel One"));
        binding.carousel.addData(new CarouselItem("https://t3.ftcdn.net/jpg/02/77/69/26/360_F_277692680_b65wdSQDuWZRrKwIUmGQo0zwND6n0MZR.jpg","Carousel Two"));

    }

    void initCategories(){
        categories = new ArrayList<>();
        categories.add(new Category("Sports","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#9C27B0","Some Description Sports",1));
        categories.add(new Category("Business","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#E91E63","Some Description Business",2));
        categories.add(new Category("Entertainment","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#3F51B5","Some Description Games",3));
        categories.add(new Category("Others","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#2196F3","Some Description Others",4));
        categories.add(new Category("Others","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#00BCD4","Some Description Others",4));
        categories.add(new Category("Entertainment","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#009688","Some Description Games",3));
        categories.add(new Category("Job","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#CDDC39","Some Description Business",2));
        categories.add(new Category("Outdoor","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","#FF5722","Some Description Sports",1));

        categoryAdapter = new CategoryAdapter(this,categories);
        binding.categoriesList.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    void initProducts(){
        products = new ArrayList<>();
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));
        products.add(new Product("Test Only","https://img.lovepik.com/png/20230929/vector-icon-category-picture-pictures-picture-album_25896_wh1200.png","Some Status",250,50,6,1));

        productAdapter = new ProductAdapter(this,products);
        binding.productList.setLayoutManager(new GridLayoutManager(this,2));
        binding.productList.setAdapter(productAdapter);

    }


}