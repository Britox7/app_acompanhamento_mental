package com.example.acompanhamentomentalandroidtrabalho.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompanhamentomentalandroidtrabalho.R;
import com.example.acompanhamentomentalandroidtrabalho.models.Emotion;
import com.google.gson.Gson;

import java.util.List;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder> {

    private final Context context;
    private final List<Emotion> emotionList;

    private static final String PREFS_NAME = "EmotionPrefs";
    private static final String KEY_EMOTION_LIST = "emotion_list";

    public EmotionAdapter(Context context, List<Emotion> emotionList) {
        this.context = context;
        this.emotionList = emotionList;
    }

    @NonNull
    @Override
    public EmotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_emotion, parent, false);
        return new EmotionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmotionViewHolder holder, int position) {
        Emotion emotion = emotionList.get(position);
        holder.tvEmotionName.setText(emotion.getEmotion());
        holder.tvEmotionDate.setText(emotion.getDate());

        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Remover emoção")
                    .setMessage("Deseja remover a emoção do dia?")
                    .setPositiveButton("Sim", (dialog, which) -> removeEmotion(position))
                    .setNegativeButton("Cancelar", null)
                    .show();
        });


        // 🔹 Long Click para remover 1 item
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Remover registro")
                    .setMessage("Deseja remover este registro?")
                    .setPositiveButton("Sim", (dialog, which) -> removeEmotion(position))
                    .setNegativeButton("Cancelar", null)
                    .show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return emotionList.size();
    }

    // 🔹 Remover item e salvar novamente no SharedPreferences
    private void removeEmotion(int position) {
        emotionList.remove(position);
        notifyItemRemoved(position);
        saveList();
    }

    private void saveList() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = new Gson().toJson(emotionList);
        prefs.edit().putString(KEY_EMOTION_LIST, json).apply();
    }

    public static class EmotionViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmotionName, tvEmotionDate;
        ImageButton btnDelete;


        public EmotionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmotionName = itemView.findViewById(R.id.tv_emotion_name);
            tvEmotionDate = itemView.findViewById(R.id.tv_emotion_date);
            btnDelete = itemView.findViewById(R.id.btn_delete_emotion); // <-- NOVO
        }
    }
}
