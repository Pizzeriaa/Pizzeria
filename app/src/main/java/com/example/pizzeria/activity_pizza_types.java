package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        pizzas.add(new Pizza(1, "Veggie Pizza", "Delicious vegetarian pizza topped with fresh vegetables.", 10.0, 0, R.drawable.veggie_pizza));
        pizzas.add(new Pizza(2, "Cheese Pizza", "Classic cheese pizza with melted mozzarella.", 8.0, 0, R.drawable.cheese_pizza));
        pizzas.add(new Pizza(3, "Pepperoni Pizza", "Savory pepperoni pizza with a generous amount of toppings.", 12.0, 0, R.drawable.pizzlogo));

        pizzaTypesAdapter = new PizzaTypesAdapter(pizzas, this);
        recyclerViewPizzaTypes.setAdapter(pizzaTypesAdapter);

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_home) {
                    return true;
                } else if (item.getItemId() == R.id.menu_cart) {
                    Intent cartIntent = new Intent(activity_pizza_types.this, activity_cart.class);
                    startActivityForResult(cartIntent, REQUEST_CODE_CART);
                    return true;
                } else if (item.getItemId() == R.id.menu_profile) {
                    return true;
                }
                return false;
            }
        });

        updateCartItemCount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CART && resultCode == RESULT_OK) {
            Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show();
            updateCartItemCount();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCartItemCount();
    }

    private void updateCartItemCount() {
        int cartItemCount = CartManager.getInstance().getCartItemCount();
        bottomNavView.getOrCreateBadge(R.id.menu_cart).setNumber(cartItemCount);
    }
}
