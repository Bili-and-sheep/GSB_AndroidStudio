package com.example.gsb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.Model.Praticien;
import com.example.gsb.R;

import java.util.List;

public class PraticienAdapter extends RecyclerView.Adapter<PraticienAdapter.ViewHolder> {

    private final List<Praticien> praticiens;

    public PraticienAdapter(List<Praticien> praticiens) {
        this.praticiens = praticiens;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_praticien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Praticien praticien = praticiens.get(position);
        holder.textViewNom.setText(praticien.getNom() + " " + praticien.getPrenom());
        holder.textViewVille.setText(praticien.getVille());
        holder.textViewEmail.setText(praticien.getEmail());
    }
    
    @Override
    public int getItemCount() {
        return praticiens != null ? praticiens.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNom, textViewVille, textViewEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewVille = itemView.findViewById(R.id.textViewVille);
            textViewEmail = itemView.findViewById(R.id.textViewSpecialite); // reuse id
        }
    }
}