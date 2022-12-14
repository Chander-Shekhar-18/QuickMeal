package com.oliver.quickmeal.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.oliver.quickmeal.Listeners.RecipeClickListener;
import com.oliver.quickmeal.R;
import com.oliver.quickmeal.apiCalls.ApiModels.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {

    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {

        holder.txtViewTitle.setText(list.get(position).title);
        holder.txtViewTitle.setSelected(true);

        holder.textViewLikes.setText(list.get(position).aggregateLikes + " Likes");
        holder.textViewServings.setText(list.get(position).servings + " Servings");
        holder.textViewTime.setText(list.get(position).readyInMinutes + " Minutes");

        Picasso
                .get()
                .load(list.get(position).image)
                .placeholder(R.drawable.loading)
                .into(holder.imgViewFood);

        holder.randomListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClicked(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

    MaterialCardView randomListContainer;
    MaterialTextView txtViewTitle, textViewServings, textViewLikes, textViewTime;
    ImageView imgViewFood;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        randomListContainer = itemView.findViewById(R.id.randomListContainer);
        txtViewTitle = itemView.findViewById(R.id.txtViewTitle);
        textViewServings = itemView.findViewById(R.id.txtViewServings);
        textViewLikes = itemView.findViewById(R.id.txtViewLikes);
        textViewTime = itemView.findViewById(R.id.txtViewTime);
        imgViewFood = itemView.findViewById(R.id.imgViewFood);
    }
}