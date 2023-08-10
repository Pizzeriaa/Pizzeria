package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_pizza_details extends AppCompatActivity {

    private TextView pizzaPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        pizzaPrice = findViewById(R.id.pizzaPrice);

        ImageView pizzaImage = findViewById(R.id.pizzaImage);
        TextView pizzaName = findViewById(R.id.pizzaName);

        // Retrieve pizza details from the intent
        Pizza pizza = getIntent().getParcelableExtra("pizza");
        if (pizza != null) {
            pizzaImage.setImageResource(pizza.getImageResourceId());
            pizzaName.setText(pizza.getName());
        }
    }

    // Method called when a size is selected
    public void onSizeSelected(View view) {
        RadioButton radioButton = (RadioButton) view;
        String size = radioButton.getText().toString();

        // Update the displayed price based on the selected size
        String price;
        switch (size) {
            case "Small":
                price = "$10";
                break;
            case "Medium":
                price = "$15";
                break;
            case "Large":
                price = "$20";
                break;
            default:
                price = "$10"; // Default to Small price
        }

        pizzaPrice.setText(price);
    }
}
