    package com.example.acompanhamentomentalandroidtrabalho.models;

    public class Emotion {
        private String emotion;
        private String date;

        // 🔹 Construtor padrão (necessário para Gson)
        public Emotion() {
        }

        // 🔹 Construtor usado ao salvar um novo sentimento
        public Emotion(String emotion, String date) {
            this.emotion = emotion;
            this.date = date;
        }

        // 🔹 Getters e Setters
        public String getEmotion() {
            return emotion;
        }

        public void setEmotion(String emotion) {
            this.emotion = emotion;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
