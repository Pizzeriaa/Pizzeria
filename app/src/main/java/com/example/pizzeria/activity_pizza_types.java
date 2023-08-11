package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class activity_pizza_types extends AppCompatActivity {

    private RecyclerView recyclerViewPizzaTypes;
    private PizzaTypesAdapter pizzaTypesAdapter;
    private static final int REQUEST_CODE_CART = 1;
    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_types);

        recyclerViewPizzaTypes = findViewById(R.id.recyclerViewPizzaTypes);
        recyclerViewPizzaTypes.setLayoutManager(new LinearLayoutManager(this));

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza("Veggie Pizza", R.drawable.veggie_pizza));
        pizzas.add(new Pizza("Cheese Pizza", R.drawable.cheese_pizza));
        pizzas.add(new Pizza("Pepperoni Pizza", R.drawable.cheese_pizza));

        pizzaTypesAdapter = new PizzaTypesAdapter(pizzas, this); // Pass the context to the adapter
        recyclerViewPizzaTypes.setAdapter(pizzaTypesAdapter);

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    // Handle home action
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    // Open cart activity
                    Intent cartIntent = new Intent(activity_pizza_types.this, activity_cart.class);
                    startActivityForResult(cartIntent, REQUEST_CODE_CART);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    // Handle profile action
                    return true;
                }
                return false;
            }
        });

        // Update cart item count when returning from the cart activity
        bottomNavView.post(new Runnable() {
            @Override
            public void run() {
                updateCartItemCount();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CART && resultCode == RESULT_OK) {
            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
            updateCartItemCount(); // Update cart item count when returning from the cart activity
        }
    }

    private void updateCartItemCount() {
        int cartItemCount = CartManager.getInstance().getCartItemCount();
        bottomNavView.getOrCreateBadge(R.id.menu_cart).setNumber(cartItemCount);
    }
}
