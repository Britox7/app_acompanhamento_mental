package com.example.acompanhamentomentalandroidtrabalho;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.acompanhamentomentalandroidtrabalho.adapters.PsychologistAdapter;
import com.example.acompanhamentomentalandroidtrabalho.models.Psychologist;
import java.util.ArrayList;
import java.util.List;

public class PsychologistsActivity extends AppCompatActivity {

    private RecyclerView rvPsychologists;
    private List<Psychologist> psychologistList;
    private PsychologistAdapter adapter;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist_list);

        // 🔹 Referência do botão de voltar
        btnBack = findViewById(R.id.btn_back);

        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }

        rvPsychologists = findViewById(R.id.rv_psychologists);
        rvPsychologists.setLayoutManager(new LinearLayoutManager(this));

        loadMockedPsychologists();

        adapter = new PsychologistAdapter(psychologistList, psychologist -> {
            new AlertDialog.Builder(this)
                    .setTitle("Conexão")
                    .setMessage("Conectado com sucesso com " + psychologist.getName() + " 😊")
                    .setPositiveButton("Fechar", null)
                    .show();
        });

        rvPsychologists.setAdapter(adapter);
    }

    private void loadMockedPsychologists() {
        psychologistList = new ArrayList<>();
        psychologistList.add(new Psychologist("Dra. Ana Souza", "Psicóloga clínica - Terapia cognitivo-comportamental"));
        psychologistList.add(new Psychologist("Dr. João Pereira", "Psicólogo - Terapia humanista"));
        psychologistList.add(new Psychologist("Dra. Maria Lima", "Psicanalista - Atendimento adulto e adolescente"));
        psychologistList.add(new Psychologist("Dr. Lucas Fernandes", "Terapia breve e acompanhamento emocional"));
    }
}
