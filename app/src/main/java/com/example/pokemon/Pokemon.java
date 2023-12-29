package com.example.pokemon;

public class Pokemon {
    private final String url;
    private final String name;

    Pokemon(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
