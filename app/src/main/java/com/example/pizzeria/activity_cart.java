package com.example.pizzeria;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class activity_cart extends AppCompatActivity {

    private RecyclerView cartRecyclerView;
    private CartAdapter cartAdapter;
    private TextView subtotalText, gstText, deliveryPriceText, totalText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        subtotalText = findViewById(R.id.subtotalText);
        gstText = findViewById(R.id.gstText);
        deliveryPriceText = findViewById(R.id.deliveryPriceText);
        totalText = findViewById(R.id.totalText);

        updateCartUI();
    }

    private void updateCartUI() {
        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        if (cartItems != null && cartItems.size() > 0) {
            cartAdapter = new CartAdapter(cartItems, this);
            cartRecyclerView.setAdapter(cartAdapter);

            double subtotal = 0;
            for (CartItem item : cartItems) {
                subtotal += item.getTotalPrice();
            }

            double gst = subtotal * 0.10;
            double total = subtotal + gst;

            subtotalText.setText(getString(R.string.price_format, subtotal));
            gstText.setText(getString(R.string.price_format, gst));
            deliveryPriceText.setText(getString(R.string.price_format, 0.0)); // Set delivery price if applicable
            totalText.setText(getString(R.string.total_price, total));
        } else {
            // Cart is empty, handle accordingly
            subtotalText.setText(getString(R.string.price_format, 0.0));
            gstText.setText(getString(R.string.price_format, 0.0));
            deliveryPriceText.setText(getString(R.string.price_format, 0.0));
            totalText.setText(getString(R.string.price_format, 0.0));
            totalText.setText("Your cart is empty");
        }
    }
}
