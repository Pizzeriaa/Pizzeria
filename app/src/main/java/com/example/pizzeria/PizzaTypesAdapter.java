// PizzaTypesAdapter.java
package com.example.pizzeria;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PizzaTypesAdapter extends RecyclerView.Adapter<PizzaTypesAdapter.ViewHolder> {

    private List<Pizza> pizzas;
    private Context context;

    public PizzaTypesAdapter(List<Pizza> pizzas, Context context) {
        this.pizzas = pizzas;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);

        holder.textViewPizzaName.setText(pizza.getName());
        holder.imageViewPizza.setImageResource(pizza.getImageResourceId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the pizza details activity
                Intent intent = new Intent(context, activity_pizza_details.class);
                intent.putExtra("pizza", pizza);  // Pass the clicked pizza to the details activity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPizzaName;
        ImageView imageViewPizza;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPizzaName = itemView.findViewById(R.id.pizzaItemName);
            imageViewPizza = itemView.findViewById(R.id.pizzaItemImage);
        }
    }
}
