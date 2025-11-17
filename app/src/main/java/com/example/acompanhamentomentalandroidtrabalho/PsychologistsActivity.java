package com.example.acompanhamentomentalandroidtrabalho;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

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

        btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> onBackPressed());
        }

        rvPsychologists = findViewById(R.id.rv_psychologists);
        rvPsychologists.setLayoutManager(new LinearLayoutManager(this));

        loadMockedPsychologists();

        adapter = new PsychologistAdapter(this, psychologistList, psychologist -> {
            String phoneNumber = psychologist.getWhatsappNumber();
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                Toast.makeText(this, "Número do WhatsApp não disponível", Toast.LENGTH_SHORT).show();
                return;
            }
            openWhatsApp(phoneNumber);
        });

        rvPsychologists.setAdapter(adapter);
    }

    private void loadMockedPsychologists() {
        psychologistList = new ArrayList<>();
        psychologistList.add(new Psychologist("Dra. Ana Souza", "Psicóloga clínica - Terapia cognitivo-comportamental", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. João Pereira", "Psicólogo - Terapia humanista", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Maria Lima", "Psicanalista - Atendimento adulto e adolescente", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Lucas Fernandes", "Terapia breve e acompanhamento emocional", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Camila Ribeiro", "Especialista em ansiedade e depressão", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Rafael Mendes", "Psicólogo clínico - Terapia comportamental", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Patrícia Moura", "Terapia para relacionamentos e autoconhecimento", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Felipe Santos", "Psicologia positiva e desenvolvimento emocional", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Larissa Azevedo", "Terapia infantil e orientação familiar", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Gustavo Nunes", "Acompanhamento psicológico adulto", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Juliana Rocha", "Psicóloga - Terapia cognitiva focada em trauma", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Marcos Albuquerque", "Psicólogo esportivo e motivacional", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Renata Siqueira", "Especialista em saúde mental e bem-estar", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. André Carvalho", "Psicanalista - Atendimento clínico", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Beatriz Farias", "Psicoterapia para autoestima e identidade", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Thiago Monteiro", "Acompanhamento emocional e suporte psicológico", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Carolina Freitas", "Psicóloga infantil e adolescente", "5588997943397"));
        psychologistList.add(new Psychologist("Dr. Eduardo Matos", "Psicólogo clínico - Terapia integrativa", "5588997943397"));
        psychologistList.add(new Psychologist("Dra. Sofia Mendes", "Terapia humanizada e acolhimento emocional", "5588997943397"));
    }

    private void openWhatsApp(String phoneNumber) {
        PackageManager packageManager = getPackageManager();

        try {
            // Verifica se o WhatsApp está instalado
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);

            Uri uri = Uri.parse("https://wa.me/" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.whatsapp");

            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "Não foi possível abrir o WhatsApp", Toast.LENGTH_SHORT).show();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp não está instalado", Toast.LENGTH_SHORT).show();
        }
    }
}
