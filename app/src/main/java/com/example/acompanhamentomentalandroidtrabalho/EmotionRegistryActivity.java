package com.example.acompanhamentomentalandroidtrabalho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.acompanhamentomentalandroidtrabalho.models.Emotion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EmotionRegistryActivity extends AppCompatActivity {

    private Spinner spinnerEmotions;
    private Button btnSaveEmotion;
    private Button btnGoHistory;
    private ImageButton btnBack;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "EmotionPrefs";
    private static final String KEY_EMOTION_LIST = "emotion_list";

    private List<Emotion> emotionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_registry);

        spinnerEmotions = findViewById(R.id.spinner_emotions);
        btnSaveEmotion = findViewById(R.id.btn_save_emotion);
        btnGoHistory = findViewById(R.id.btn_go_history);
        btnBack = findViewById(R.id.btn_back);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadEmotions();

        // ⬇️ SPINNER CUSTOMIZADO — ESTA É A ALTERAÇÃO QUE VOCÊ PEDIU
        String[] emotions = {
                "Feliz 😊",
                "Triste 😢",
                "Ansioso 😰",
                "Calmo 😌",
                "Irritado 😠",
                "Cansado 😴",
                "Motivado 💪"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.spinner_item,
                emotions
        );

        adapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerEmotions.setAdapter(adapter);
        // ⬆️ FIM DA PARTE IMPLEMENTADA

        btnSaveEmotion.setOnClickListener(v -> saveEmotion());
        btnBack.setOnClickListener(v -> finish());

        btnGoHistory.setOnClickListener(v -> {
            Intent intent = new Intent(this, EmotionHistoryActivity.class);
            startActivity(intent);
        });
    }

    private void loadEmotions() {
        String json = sharedPreferences.getString(KEY_EMOTION_LIST, "[]");
        Type type = new TypeToken<List<Emotion>>() {}.getType();
        emotionList = new Gson().fromJson(json, type);

        if (emotionList == null) emotionList = new ArrayList<>();
    }

    private void saveEmotion() {
        String selectedEmotion = spinnerEmotions.getSelectedItem().toString();
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        for (Emotion emotion : emotionList) {
            if (emotion.getDate().equals(date)) {
                Toast.makeText(this, "Você já registrou um sentimento hoje!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        emotionList.add(0, new Emotion(selectedEmotion, date));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_EMOTION_LIST, new Gson().toJson(emotionList));
        editor.apply();

        Toast.makeText(this, "Sentimento do dia salvo!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, EmotionHistoryActivity.class);
        startActivity(intent);
        finish();
    }
}
