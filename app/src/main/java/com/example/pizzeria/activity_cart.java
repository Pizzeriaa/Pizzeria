package com.example.pizzeria;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class activity_cart extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Retrieve cart items from CartManager
        cartAdapter = new CartAdapter(CartManager.getInstance().getCartItems());
        cartRecyclerView.setAdapter(cartAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Update cart adapter when returning from the pizza types activity
        cartAdapter.notifyDataSetChanged();
    }
}
