package com.example.acompanhamentomentalandroidtrabalho;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompanhamentomentalandroidtrabalho.R;
import com.example.acompanhamentomentalandroidtrabalho.adapters.EmotionAdapter;
import com.example.acompanhamentomentalandroidtrabalho.models.Emotion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmotionHistoryActivity extends AppCompatActivity {

    private RecyclerView rvEmotions;
    private ImageButton btnBack;
    private List<Emotion> emotionList;
    private EmotionAdapter emotionAdapter;
    private SharedPreferences sharedPreferences;

    private static final String PREFS_NAME = "EmotionPrefs";
    private static final String KEY_EMOTION_LIST = "emotion_list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_history);

        rvEmotions = findViewById(R.id.rv_emotions);
        btnBack = findViewById(R.id.btn_back);

        rvEmotions.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadEmotions();

        emotionAdapter = new EmotionAdapter(this, emotionList);
        rvEmotions.setAdapter(emotionAdapter);

        btnBack.setOnClickListener(v -> finish());
    }

    private void loadEmotions() {
        String json = sharedPreferences.getString(KEY_EMOTION_LIST, "[]");
        Type type = new TypeToken<List<Emotion>>() {}.getType();
        emotionList = new Gson().fromJson(json, type);
        if (emotionList == null) emotionList = new ArrayList<>();
    }
}