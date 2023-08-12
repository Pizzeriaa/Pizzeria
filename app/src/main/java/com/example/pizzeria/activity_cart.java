package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class activity_cart extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView subtotalText, gstText, deliveryPriceText, totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the TextViews for bill details
        subtotalText = findViewById(R.id.subtotalText);
        gstText = findViewById(R.id.gstText);
        deliveryPriceText = findViewById(R.id.deliveryPriceText);
        totalText = findViewById(R.id.totalText);

        // Retrieve cart items from CartManager
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();
        cartAdapter = new CartAdapter(cartItems, this);
        cartRecyclerView.setAdapter(cartAdapter);

        // Calculate and display bill details
        updateBillDetails();

        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Implement checkout functionality
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Update cart adapter when returning from the pizza types activity
        cartAdapter.notifyDataSetChanged();

        // Calculate and display bill details
        updateBillDetails();
    }

    private void updateBillDetails() {
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        double subtotal = 0;
        for (CartItem cartItem : cartItems) {
            subtotal += cartItem.getTotalPrice();
        }
        subtotalText.setText(getString(R.string.subtotal) + ": $" + String.format(Locale.getDefault(), "%.2f", subtotal));

        double gst = subtotal * 0.1; // Assuming GST is 10%
        gstText.setText(getString(R.string.gst) + ": $" + String.format(Locale.getDefault(), "%.2f", gst));

        double deliveryPrice = 5.0; // Example delivery price
        deliveryPriceText.setText(getString(R.string.delivery_price) + ": $" + String.format(Locale.getDefault(), "%.2f", deliveryPrice));

        double total = subtotal + gst + deliveryPrice;
        totalText.setText(getString(R.string.total) + ": $" + String.format(Locale.getDefault(), "%.2f", total));
    }

}
