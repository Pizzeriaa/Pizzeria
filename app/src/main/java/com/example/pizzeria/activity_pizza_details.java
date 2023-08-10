package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_pizza_details extends AppCompatActivity {

    private TextView pizzaPrice;
    private TextView pizzaQuantity; // Added TextView for quantity display
    private int quantity = 1;
    private double basePrice = 10.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        pizzaPrice = findViewById(R.id.pizzaPrice);
        pizzaQuantity = findViewById(R.id.pizzaQuantity); // Initialize quantity TextView

        ImageView pizzaImage = findViewById(R.id.pizzaImage);
        TextView pizzaName = findViewById(R.id.pizzaName);

        // Retrieve pizza details from the intent
        Pizza pizza = getIntent().getParcelableExtra("pizza");
        if (pizza != null) {
            pizzaImage.setImageResource(pizza.getImageResourceId());
            pizzaName.setText(pizza.getName());
        }

        // Set initial price
        updatePrice();
        updateQuantity(); // Initialize quantity TextView

        // Quantity controls
        ImageButton decreaseQuantityButton = findViewById(R.id.decreaseQuantityButton);
        ImageButton increaseQuantityButton = findViewById(R.id.increaseQuantityButton);

        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    updateQuantity(); // Update quantity TextView
                    updatePrice();
                }
            }
        });

        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                updateQuantity(); // Update quantity TextView
                updatePrice();
            }
        });
    }

    // Method called when a size is selected
    public void onSizeSelected(View view) {
        RadioButton radioButton = (RadioButton) view;
        String size = radioButton.getText().toString();

        switch (size) {
            case "Small":
                basePrice = 10.0;
                break;
            case "Medium":
                basePrice = 15.0;
                break;
            case "Large":
                basePrice = 20.0;
                break;
            default:
                basePrice = 10.0; // Default to Small price
        }

        updatePrice();
    }

    private void updatePrice() {
        double totalPrice = basePrice * quantity;
        pizzaPrice.setText(getString(R.string.pizza_price, totalPrice));
    }

    private void updateQuantity() {
        pizzaQuantity.setText(String.valueOf(quantity)); // Update quantity TextView
    }
}
