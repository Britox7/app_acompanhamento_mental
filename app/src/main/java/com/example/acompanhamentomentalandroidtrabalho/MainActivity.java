package com.example.acompanhamentomentalandroidtrabalho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CardView cardPsychologists;
    private CardView cardEmotions;
    private CardView cardNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar os cards
        cardPsychologists = findViewById(R.id.card_psychologists);
        cardEmotions = findViewById(R.id.card_emotions);
        cardNotes = findViewById(R.id.card_notes);

        // Configurar cliques nos cards
        setupClickListeners();
    }

    private void setupClickListeners() {
        // Card 1: Lista de Psicólogos
        cardPsychologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PsychologistListActivity.class);
                startActivity(intent);
            }
        });

        // Card 2: Registrar Emoção
        cardEmotions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmotionRegistryActivity.class);
                startActivity(intent);
            }
        });

        // Card 3: Bloco de Notas
        cardNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });
    }
}