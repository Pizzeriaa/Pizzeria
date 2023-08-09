// PizzaTypesAdapter.java
package com.example.pizzeria;

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

    public PizzaTypesAdapter(List<Pizza> pizzas) {
        this.pizzas = pizzas;
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
