package com.example.acompanhamentomentalandroidtrabalho.models;

public class Psychologist {
    private String name;
    private String description;

    public Psychologist(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
