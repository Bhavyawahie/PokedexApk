package com.example.pokemon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PokedexAdapter extends RecyclerView.Adapter<PokedexViewHolder> {
    private List<Pokemon> pokemonList = new ArrayList<>();
    private RequestQueue requestQueue;

    PokedexAdapter(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        loadPokemon();
    }

    @SuppressLint("NewApi")
    private Response.Listener<JSONObject> responseListenerFunction = response -> {
        try {
            JSONArray results = response.getJSONArray("results");
            pokemonList.addAll(IntStream.range(0, results.length()).mapToObj(i -> {
                try {
                    return results.getJSONObject(i);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }).map(r -> {
                try {
                    String name = r.getString("name").substring(0,1).toUpperCase() + r.getString("name").substring(1);
                    return new Pokemon(r.getString("url"), name);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList()));
            notifyDataSetChanged();
        } catch (JSONException e) {
            Log.e("API loading error", "Error in JSON response", e);
        }
    };
    private Response.ErrorListener errorListenerFunction = volleyError -> Log.e("API loading error", "Error in Listing data");
    public void loadPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon/?limit=150";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, responseListenerFunction, errorListenerFunction);
        requestQueue.add(request);
    }
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
