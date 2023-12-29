package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PokemonInfoChildActivity extends AppCompatActivity {
    private TextView pokemonName;
    private TextView pokemonUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info_child);

        String name = getIntent().getStringExtra("name");
        SpannableString url = new SpannableString(getIntent().getStringExtra("url"));
        pokemonName = findViewById(R.id.pokemon_name);
        pokemonUrl = findViewById(R.id.pokemon_id);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Handle the click event by opening the browser to the specified URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()));
                startActivity(intent);
            }
        };

        // Set the ClickableSpan to the specific part of the text
        url.setSpan(clickableSpan, 0, url.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        pokemonName.setText(name);
        pokemonUrl.setText(url);
        pokemonUrl.setMovementMethod(LinkMovementMethod.getInstance());
    }
}