package com.example.acompanhamentomentalandroidtrabalho.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.acompanhamentomentalandroidtrabalho.R;
import com.example.acompanhamentomentalandroidtrabalho.models.Psychologist;
import java.util.List;

public class PsychologistAdapter extends RecyclerView.Adapter<PsychologistAdapter.PsychologistViewHolder> {

    public interface OnConnectClickListener {
        void onConnectClick(Psychologist psychologist);
    }

    private List<Psychologist> psychologistList;
    private OnConnectClickListener listener;

    public PsychologistAdapter(List<Psychologist> psychologistList, OnConnectClickListener listener) {
        this.psychologistList = psychologistList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PsychologistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_psychologist, parent, false);
        return new PsychologistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PsychologistViewHolder holder, int position) {
        Psychologist psychologist = psychologistList.get(position);
        holder.tvName.setText(psychologist.getName());
        holder.tvDescription.setText(psychologist.getDescription());

        holder.btnConnect.setOnClickListener(v -> listener.onConnectClick(psychologist));
    }

    @Override
    public int getItemCount() {
        return psychologistList.size();
    }

    static class PsychologistViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription;
        Button btnConnect;

        public PsychologistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_psychologist_name);
            tvDescription = itemView.findViewById(R.id.tv_psychologist_description);
            btnConnect = itemView.findViewById(R.id.btn_connect);
        }
    }
}
