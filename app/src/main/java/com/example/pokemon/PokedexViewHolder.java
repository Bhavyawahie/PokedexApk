package com.example.pokemon;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PokedexViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout linearLayoutContainer;
    private TextView textView;

    PokedexViewHolder(View view) {
        super(view);
        linearLayoutContainer = view.findViewById(R.id.pokedex_row);
        textView = view.findViewById(R.id.pokedex_row_text_view);
        linearLayoutContainer.setOnClickListener(v -> {
            Pokemon clickedPokemonItem = (Pokemon) linearLayoutContainer.getTag();
            Intent intent = new Intent(v.getContext(), PokemonInfoChildActivity.class);
            intent.putExtra("name", clickedPokemonItem.getName());
            intent.putExtra("url", clickedPokemonItem.getUrl());

            v.getContext().startActivity(intent);
        });
    }

    public LinearLayout getLinearLayoutContainer() {
        return linearLayoutContainer;
    }

    public void setLinearLayoutContainer(LinearLayout linearLayoutContainer) {
        this.linearLayoutContainer = linearLayoutContainer;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
}
