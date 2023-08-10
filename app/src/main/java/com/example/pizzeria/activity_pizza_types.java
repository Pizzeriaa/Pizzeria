package com.example.pizzeria;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class activity_pizza_types extends AppCompatActivity {

    private RecyclerView recyclerViewPizzaTypes;
    private PizzaTypesAdapter pizzaTypesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_types);

        recyclerViewPizzaTypes = findViewById(R.id.recyclerViewPizzaTypes);
        recyclerViewPizzaTypes.setLayoutManager(new LinearLayoutManager(this));

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(new Pizza("Veggie Pizza", R.drawable.veggie_pizza));
        pizzas.add(new Pizza("Cheese Pizza", R.drawable.cheese_pizza));
        pizzas.add(new Pizza("Pepperoni Pizza", R.drawable.pizzlogo));

        pizzaTypesAdapter = new PizzaTypesAdapter(pizzas, this); // Pass the context to the adapter
        recyclerViewPizzaTypes.setAdapter(pizzaTypesAdapter);
    }
}
