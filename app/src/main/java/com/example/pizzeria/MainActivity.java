package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottomNavView);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_home) {
                    // Handle Home action
                } else if (itemId == R.id.menu_cart) {
                    // Handle Cart action
                } else if (itemId == R.id.menu_orders) {
                    // Handle Orders action
                } else if (itemId == R.id.menu_profile) {
                    // Handle Profile action
                }
                return true;
            }
        });

        // Get a reference to your Pizzas category layout
        LinearLayout pizzaCategoryLayout = findViewById(R.id.pizzaCategoryLayout);

        // Set a click listener on the pizzaCategoryLayout
        pizzaCategoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the PizzaTypesActivity
                Intent intent = new Intent(MainActivity.this, activity_pizza_types.class);

                // Start the activity
                startActivity(intent);
            }
        });
    }
}
