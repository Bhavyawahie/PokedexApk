package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PokemonInfoChildActivity extends AppCompatActivity {
    private TextView pokemonName;
    private TextView pokemonId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info_child);

        String name = getIntent().getStringExtra("name");
        int id = getIntent().getIntExtra("number", 0);
        pokemonName = findViewById(R.id.pokemon_name);
        pokemonId = findViewById(R.id.pokemon_id);

        pokemonName.setText(name);
        pokemonId.setText(Integer.toString(id));
    }
}