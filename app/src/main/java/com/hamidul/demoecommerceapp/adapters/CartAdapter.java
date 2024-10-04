package com.hamidul.demoecommerceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hamidul.demoecommerceapp.R;
import com.hamidul.demoecommerceapp.databinding.ItemCartBinding;
import com.hamidul.demoecommerceapp.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartViewHolder>{

    Context context;
    ArrayList<Product> products;
    Toast toast;
    CartListener cartListener;
    Cart cart;

    public interface CartListener {

        public void onQuantityChanged();

    }

    public CartAdapter(Context context, ArrayList<Product> products, CartListener cartListener) {
        this.context = context;
        this.products = products;
        this.cartListener = cartListener;
        cart = TinyCartHelper.getCart();
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new cartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);

        int quantity = product.getQuantity();

        holder.binding.name.setText(product.getName());
        holder.binding.price.setText(String.format("BDT %.2f",product.getPrice()));
        holder.binding.quantity.setText(String.valueOf(product.getQuantity()));
        holder.binding.totalPrice.setText(String.format("BDT %.2f",product.getPrice()*quantity));

        holder.binding.productStock.setText("Stock : "+product.getStock());

        holder.binding.negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = product.getQuantity();

                if (quantity>1) quantity--;

                product.setQuantity(quantity);
                holder.binding.quantity.setText(String.valueOf(quantity));
//                holder.binding.totalPrice.setText(String.format("BDT %.2f",product.getPrice()*quantity));

                notifyDataSetChanged();
                cart.updateItem(product,product.getQuantity());
                cartListener.onQuantityChanged();

            }
        });

        holder.binding.positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = product.getQuantity();

                quantity++;

                if (quantity>product.getStock()){
                    setToast("Max stock available : "+product.getQuantity());
                    return;
                }else {
                    product.setQuantity(quantity);
                    holder.binding.quantity.setText(String.valueOf(quantity));
//                    holder.binding.totalPrice.setText(String.format("BDT %.2f",product.getPrice()*quantity));
                }

                notifyDataSetChanged();
                cart.updateItem(product,product.getQuantity());
                cartListener.onQuantityChanged();

            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//
//                QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));
//                AlertDialog dialog = new AlertDialog.Builder(context)
//                        .setView(quantityDialogBinding.getRoot())
//                        .create();
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//
//                quantityDialogBinding.noBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                    }
//                });
//
//                quantityDialogBinding.yesBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        cart.removeItem(product);
//                        notifyDataSetChanged();
//                        dialog.dismiss();
//                    }
//                });
//
//                return true;
//
//            }
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));
//
//                AlertDialog dialog = new AlertDialog.Builder(context)
//                        .setView(quantityDialogBinding.getRoot())
//                        .create();
//
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//                quantityDialogBinding.productName.setText(product.getName());
//                quantityDialogBinding.productStock.setText("Stock : "+product.getStock());
//                quantityDialogBinding.quantity.setText(String.valueOf(product.getQuantity()));
//
//                int stock = product.getStock();
//
//                quantityDialogBinding.negativeBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        int quantity = product.getQuantity();
//
//                        if (quantity>1) quantity--;
//
//                        product.setQuantity(quantity);
//                        quantityDialogBinding.quantity.setText(String.valueOf(quantity));
//
//                        notifyDataSetChanged();
//                        cart.updateItem(product,product.getQuantity());
//                        cartListener.onQuantityChanged();
//
//                    }
//                });
//
//                quantityDialogBinding.positiveBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        int quantity = product.getQuantity();
//
//                        quantity++;
//
//                        if (quantity>product.getStock()){
//                            setToast("Max stock available : "+product.getQuantity());
//                            return;
//                        }else {
//                            product.setQuantity(quantity);
//                            quantityDialogBinding.quantity.setText(String.valueOf(quantity));
//                        }
//
//                        notifyDataSetChanged();
//                        cart.updateItem(product,product.getQuantity());
//                        cartListener.onQuantityChanged();
//
//                    }
//                });
//
//                quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        notifyDataSetChanged();
//                        cart.updateItem(product,product.getQuantity());
//                        cartListener.onQuantityChanged();
//                    }
//                });
//
//                dialog.show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder{


        ItemCartBinding binding;
        public cartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemCartBinding.bind(itemView);
        }
    }

    private void setToast (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(context,message,Toast.LENGTH_LONG);
        toast.show();
    }


}
