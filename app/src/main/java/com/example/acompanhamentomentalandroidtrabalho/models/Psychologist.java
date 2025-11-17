package com.example.acompanhamentomentalandroidtrabalho.models;

public class Psychologist {
    private String name;
    private String description;
    private String whatsappNumber; // novo campo


    public Psychologist(String name, String description,String whatsappNumber) {
        this.name = name;
        this.description = description;
        this.whatsappNumber = whatsappNumber;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }
}
