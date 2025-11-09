package com.example.acompanhamentomentalandroidtrabalho;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.acompanhamentomentalandroidtrabalho.adapters.NoteAdapter;
import com.example.acompanhamentomentalandroidtrabalho.models.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

public class NotesActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private EditText etNotes;
    private Button btnSave;
    private TextView tvLastSaved, tvCharCount;
    private RecyclerView rvNotes;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "NotesPrefs";
    private static final String KEY_NOTES_LIST = "notes_list";

    private List<Note> noteList;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        btnBack = findViewById(R.id.btn_back);
        etNotes = findViewById(R.id.et_notes);
        btnSave = findViewById(R.id.btn_save);
        tvLastSaved = findViewById(R.id.tv_last_saved);
        tvCharCount = findViewById(R.id.tv_char_count);
        rvNotes = findViewById(R.id.rv_notes);

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        loadNotes();

        noteAdapter = new NoteAdapter(this, noteList);
        rvNotes.setAdapter(noteAdapter);


        setupListeners();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> saveNote());

        etNotes.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCharCount(s.length());
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void loadNotes() {
        String json = sharedPreferences.getString(KEY_NOTES_LIST, "[]");
        Type type = new TypeToken<List<Note>>() {}.getType();
        noteList = new Gson().fromJson(json, type);
        if (noteList == null) noteList = new ArrayList<>();
    }

    private void saveNote() {
        String content = etNotes.getText().toString().trim();
        if (content.isEmpty()) {
            Toast.makeText(this, "Digite algo antes de salvar!", Toast.LENGTH_SHORT).show();
            return;
        }

        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date());
        noteList.add(0, new Note(content, date));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NOTES_LIST, new Gson().toJson(noteList));
        editor.apply();

        etNotes.setText("");
        tvLastSaved.setText("Última edição: " + date);
        noteAdapter.notifyDataSetChanged();

        Toast.makeText(this, "Anotação salva!", Toast.LENGTH_SHORT).show();
    }

    private void updateCharCount(int count) {
        tvCharCount.setText(count + " caracteres");
    }
}
