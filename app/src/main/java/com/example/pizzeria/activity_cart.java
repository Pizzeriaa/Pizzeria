package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class activity_cart extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize RecyclerView
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve cart item data from intent extras
        Intent intent = getIntent();
        if (intent != null) {
            CartItem cartItem = intent.getParcelableExtra("cartItem");
            if (cartItem != null) {
                cartItemList = new ArrayList<>();
                cartItemList.add(cartItem);
            }
        } else {
            // Initialize an empty cartItemList if no data from intent
            cartItemList = new ArrayList<>();
        }

        // Initialize adapter and set it to RecyclerView
        cartAdapter = new CartAdapter(cartItemList);
        cartRecyclerView.setAdapter(cartAdapter);
    }
}
