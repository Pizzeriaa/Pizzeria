package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_pizza_details extends AppCompatActivity {

    private TextView pizzaPrice;
    private TextView pizzaQuantity;
    private int quantity = 1;
    private double basePrice = 10.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        pizzaPrice = findViewById(R.id.pizzaPrice);
        pizzaQuantity = findViewById(R.id.pizzaQuantity);

        ImageView pizzaImage = findViewById(R.id.pizzaImage);
        TextView pizzaName = findViewById(R.id.pizzaName);

        // Retrieve pizza details from the intent
        Pizza pizza = getIntent().getParcelableExtra("pizza");
        if (pizza != null) {
            pizzaImage.setImageResource(pizza.getImageResourceId());
            pizzaName.setText(pizza.getName());
        }

        updatePrice();
        updateQuantity();

        ImageButton decreaseQuantityButton = findViewById(R.id.decreaseQuantityButton);
        ImageButton increaseQuantityButton = findViewById(R.id.increaseQuantityButton);

        decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    updateQuantity();
                    updatePrice();
                }
            }
        });

        increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                updateQuantity();
                updatePrice();
            }
        });

        Button addToCartButton = findViewById(R.id.addToCartButton);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pizzaNameText = pizzaName.getText().toString();
                String pizzaPriceText = pizzaPrice.getText().toString();

                // Create a cart item
                CartItem cartItem = new CartItem(pizzaNameText, pizzaPriceText, quantity);

                // Add the item to the cart using CartManager
                CartManager.getInstance().addItemToCart(cartItem);

                // Notify the user
                Toast.makeText(activity_pizza_details.this, "Item added to cart", Toast.LENGTH_SHORT).show();

                // Finish the activity
                finish();
            }
        });
    }

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
                basePrice = 10.0;
        }

        updatePrice();
    }

    private void updatePrice() {
        double totalPrice = basePrice * quantity;
        pizzaPrice.setText(getString(R.string.pizza_price, totalPrice));
    }

    private void updateQuantity() {
        pizzaQuantity.setText(String.valueOf(quantity));
    }
}
