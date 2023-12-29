package com.example.pokemon;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokemonInfoChildActivity extends AppCompatActivity {
    private TextView pokemonName;
    private TextView pokemonId;
    private TextView pokemonUrl;
    private TextView pokemonType1;
    private TextView pokemonType2;
    private String url;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_info_child);

        //initializing requestqueue with the application context of the activity(directly)
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //retrieving the extra string containing url from intent of previous activity
        url = getIntent().getStringExtra("url");

        //reference views to corresponding variables
        pokemonName = findViewById(R.id.pokemon_name);
        pokemonId = findViewById(R.id.pokemon_id) ;
        pokemonUrl = findViewById(R.id.pokemon_url);
        pokemonType1 = findViewById(R.id.pokemon_type1);
        pokemonType2 = findViewById(R.id.pokemon_type2);

        //make the get request and load the JSON response
        load();
    }

    @SuppressLint("NewApi")
    private Response.Listener<JSONObject> responseListenerFunction = response -> {
        try {
            //added a hyperlink-styling to string
            SpannableString link = new SpannableString(url);

            //method to define the action on click of the clickable-span
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
            };

            //attaching the styled hyperlink with clickable-span and thus adding functionality (onClick)
            link.setSpan(clickableSpan, 0, link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            //populating the views
            pokemonName.setText(response.getString("name"));
            pokemonId.setText("id: " + String.format("%07d", response.getInt("id")));
            pokemonUrl.setText(link);

            //makes the view as a Link-clickable
            pokemonUrl.setMovementMethod(LinkMovementMethod.getInstance());


            /*  referencing the Array inside of JSON response object by it's name and
                then iterating over the it to retrieve the nested Object's respective keys i.e (Obj)slot and (Obj)type
            */
            JSONArray types = response.getJSONArray("types");
            for (int i = 0; i < types.length(); i++) {
                try {
                    JSONObject typeEntry = types.getJSONObject(i);
                    int slot = typeEntry.getInt("slot");
                    String type = typeEntry.getJSONObject("type").getString("name");
                    if (slot == 1) {
                        pokemonType1.setText(type);
                    } else if(slot == 2 ){
                        pokemonType2.setText(type);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (JSONException e) {
            Log.e("API loading error", "Error in JSON response", e);
        }
    };

    private Response.ErrorListener errorListenerFunction = volleyError -> Log.e("API loading error", "Error in Listing data");

    protected void load() {
        pokemonType1.setText("");
        pokemonType2.setText("");
        //Creating the request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListenerFunction, errorListenerFunction);
        //adding the request to queue which will be fired once the load() method is invoked
        requestQueue.add(request);
    }
}