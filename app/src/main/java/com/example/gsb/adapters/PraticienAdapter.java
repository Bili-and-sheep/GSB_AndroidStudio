package com.example.gsb.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.Model.Praticien;
import com.example.gsb.R;

import java.util.List;

public class PraticienAdapter extends RecyclerView.Adapter<PraticienAdapter.PraticienViewHolder> {

    private Context context;
    private List<Praticien> praticiens;

    public PraticienAdapter(Context context, List<Praticien> praticiens) {
        this.context = context;
        this.praticiens = praticiens;
    }

    @Override
    public PraticienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_praticien, parent, false);
        return new PraticienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PraticienViewHolder holder, int position) {
        Praticien praticien = praticiens.get(position);
        holder.textViewNom.setText(praticien.getNom() + " " + praticien.getPrenom());
        holder.textViewVille.setText(praticien.getVille());
        holder.textViewEmail.setText(praticien.getEmail()); // Remplacer par spécialité si tu en as une
    }

    @Override
    public int getItemCount() {
        return praticiens != null ? praticiens.size() : 0;
    }

    public static class PraticienViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNom, textViewVille, textViewEmail, tvSpecialite;

        public PraticienViewHolder(View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewVille = itemView.findViewById(R.id.textViewVille);
            textViewEmail = itemView.findViewById(R.id.textViewEmail); // reuse id
        }
    }
}