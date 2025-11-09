package com.example.acompanhamentomentalandroidtrabalho.models;

public class Note {
    private String content;
    private String date;

    public Note(String content, String date) {
        this.content = content;
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
