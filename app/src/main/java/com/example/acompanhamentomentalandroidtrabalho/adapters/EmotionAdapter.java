package com.example.acompanhamentomentalandroidtrabalho.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompanhamentomentalandroidtrabalho.R;
import com.example.acompanhamentomentalandroidtrabalho.models.Emotion;

import java.util.List;

public class EmotionAdapter extends RecyclerView.Adapter<EmotionAdapter.EmotionViewHolder> {

    private final Context context;
    private final List<Emotion> emotionList;

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
    }

    @Override
    public int getItemCount() {
        return emotionList.size();
    }

    public static class EmotionViewHolder extends RecyclerView.ViewHolder {
        TextView tvEmotionName, tvEmotionDate;

        public EmotionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEmotionName = itemView.findViewById(R.id.tv_emotion_name);
            tvEmotionDate = itemView.findViewById(R.id.tv_emotion_date);
        }
    }
}