package com.example.pokemon;

public class Pokemon {
    private final int id;
    private final String name;

    Pokemon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
