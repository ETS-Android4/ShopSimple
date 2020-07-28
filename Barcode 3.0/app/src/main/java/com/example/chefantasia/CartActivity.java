package com.example.chefantasia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.chefantasia.Model.Cart;
import com.example.chefantasia.ViewHolder.CartViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button submitButton;
    private TextView txtTotalAmount;

    FirebaseRecyclerAdapter<com.example.chefantasia.Model.Cart, CartViewHolder>adapter;

    FirebaseDatabase database;
    DatabaseReference Cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        submitButton = (Button) findViewById(R.id.submitBtn);
        txtTotalAmount = (TextView) findViewById(R.id.total_price);

        database = FirebaseDatabase.getInstance();
        Cart = database.getReference("Cart");

        loadCart();
    }

    private void loadCart(){
        adapter = new FirebaseRecyclerAdapter<com.example.chefantasia.Model.Cart, CartViewHolder>(com.example.chefantasia.Model.Cart.class, R.layout.cart_items_layout, CartViewHolder.class, Cart) {
            @Override
            protected void populateViewHolder(CartViewHolder cartViewHolder, com.example.chefantasia.Model.Cart cart, int i) {
                cartViewHolder.productNAME.setText(cart.getName());
                cartViewHolder.productPRICE.setText(cart.getPrice());
                cartViewHolder.productQUANTITY.setText(cart.getQuantity());
            }
        };
        recyclerView.setAdapter(adapter);
    }


}