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
import com.example.acompanhamentomentalandroidtrabalho.models.Note;
import com.google.gson.Gson;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes;
    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "NotesPrefs";
    private static final String KEY_NOTES_LIST = "notes_list";

    public NoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvNoteContent.setText(note.getContent());
        holder.tvNoteDate.setText(note.getDate());

        // 🗑️ Excluir anotação
        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Excluir anotação")
                    .setMessage("Deseja realmente excluir esta anotação?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        notes.remove(position);
                        notifyItemRemoved(position);
                        saveNotes();
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        // 👁️ Clique para visualizar a anotação em um pop-up
        holder.itemView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Anotação - " + note.getDate());
            builder.setMessage(note.getContent());
            builder.setPositiveButton("Fechar", null);
            builder.show();
        });
    }

    private void saveNotes() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NOTES_LIST, new Gson().toJson(notes));
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvNoteContent, tvNoteDate;
        ImageButton btnDelete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoteContent = itemView.findViewById(R.id.tv_note_content);
            tvNoteDate = itemView.findViewById(R.id.tv_note_date);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
