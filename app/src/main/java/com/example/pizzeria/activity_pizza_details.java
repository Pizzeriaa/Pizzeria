package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class activity_pizza_details extends AppCompatActivity {

    private ImageView pizzaImage;
    private RadioGroup pizzaSizeGroup;
    private RadioButton sizeSmall, sizeMedium, sizeLarge;
    private ImageView decreaseQuantityButton, increaseQuantityButton;
    private TextView pizzaQuantity, pizzaPrice;
    private Button addToCartButton;
    private BottomNavigationView bottomNavView;

    private int currentQuantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        pizzaImage = findViewById(R.id.pizzaImage);
        pizzaSizeGroup = findViewById(R.id.pizzaSizeGroup);
        sizeSmall = findViewById(R.id.sizeSmall);
        sizeMedium = findViewById(R.id.sizeMedium);
        sizeLarge = findViewById(R.id.sizeLarge);
        decreaseQuantityButton = findViewById(R.id.decreaseQuantityButton);
        increaseQuantityButton = findViewById(R.id.increaseQuantityButton);
        pizzaQuantity = findViewById(R.id.pizzaQuantity);
        pizzaPrice = findViewById(R.id.pizzaPrice);
        addToCartButton = findViewById(R.id.addToCartButton);
        bottomNavView = findViewById(R.id.bottomNavView);

        pizzaSizeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updatePizzaPrice();
            }
        });

        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuantity > 1) {
                    currentQuantity--;
                    updateQuantityText();
                    updatePizzaPrice();
                }
            }
        });

        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentQuantity++;
                updateQuantityText();
                updatePizzaPrice();
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pizzaSize = getSelectedSize();
                // TODO: Add item to cart with selected size and quantity
                String message = "Added " + currentQuantity + " " + pizzaSize + " pizza to cart";
                Toast.makeText(activity_pizza_details.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle bottom navigation item clicks
                return true;
            }
        });

        updateQuantityText();
        updatePizzaPrice();

        // Retrieve the selected pizza object from the intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("pizza")) {
            Pizza selectedPizza = intent.getParcelableExtra("pizza");
            if (selectedPizza != null) {
                // Update the pizza image based on the selected pizza's image resource ID
                pizzaImage.setImageResource(selectedPizza.getImageResourceId());
            }
        }
    }

    private void updateQuantityText() {
        pizzaQuantity.setText(String.valueOf(currentQuantity));
    }

    private String getSelectedSize() {
        int selectedId = pizzaSizeGroup.getCheckedRadioButtonId();
        if (selectedId == sizeSmall.getId()) {
            return "Small";
        } else if (selectedId == sizeMedium.getId()) {
            return "Medium";
        } else if (selectedId == sizeLarge.getId()) {
            return "Large";
        }
        return "";
    }

    private void updatePizzaPrice() {
        double basePrice = getSelectedBasePrice();
        double totalPrice = basePrice * currentQuantity;
        pizzaPrice.setText(String.format("$%.2f", totalPrice));
    }

    private double getSelectedBasePrice() {
        int selectedId = pizzaSizeGroup.getCheckedRadioButtonId();
        if (selectedId == sizeSmall.getId()) {
            return 8.99;
        } else if (selectedId == sizeMedium.getId()) {
            return 10.99;
        } else if (selectedId == sizeLarge.getId()) {
            return 12.99;
        }
        return 0;
    }
}
