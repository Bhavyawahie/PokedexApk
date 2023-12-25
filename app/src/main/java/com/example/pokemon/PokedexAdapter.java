package com.example.pokemon;

import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexViewHolder> {
    List<Pokemon> pokemonList = Arrays.asList(
            new Pokemon(1, "Pikachu"),
            new Pokemon(2, "Bulbasaur"),
            new Pokemon(3, "Jigglypuff"),
            new Pokemon(4, "Charmander"),
            new Pokemon(5, "Squirtle"),
            new Pokemon(6, "Meowth"),
            new Pokemon(7, "Psyduck"),
            new Pokemon(8, "Geodude"),
            new Pokemon(9, "Magnemite"),
            new Pokemon(10, "Mankey"),
            new Pokemon(11, "Growlithe"),
            new Pokemon(12, "Poliwag"),
            new Pokemon(13, "Abra"),
            new Pokemon(14, "Machop"),
            new Pokemon(15, "Bellsprout"),
            new Pokemon(16, "Tentacool"),
            new Pokemon(17, "Geodude"),
            new Pokemon(18, "Ponyta")
    );
    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroupParent, int viewType) {
        View view = LayoutInflater.from(viewGroupParent.getContext()).inflate(R.layout.pokedex_row, viewGroupParent, false);
        return new PokedexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder pokedexViewHolder, int position) {
        Pokemon currentListItem = pokemonList.get(position);
        pokedexViewHolder.getTextView().setText(currentListItem.getName());

        pokedexViewHolder.getLinearLayoutContainer().setTag(currentListItem);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
