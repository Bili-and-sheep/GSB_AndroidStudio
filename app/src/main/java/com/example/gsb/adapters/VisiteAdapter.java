package com.example.gsb.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.Activities.VisiteDetailActivity;
import com.example.gsb.Model.Visite;
import com.example.gsb.R;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class VisiteAdapter extends RecyclerView.Adapter<VisiteAdapter.VisiteViewHolder> {

    private List<Visite> visiteList;

    public VisiteAdapter(List<Visite> visiteList) {
        this.visiteList = visiteList;
    }

    @NonNull
    @Override
    public VisiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_visite, parent, false);
        return new VisiteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VisiteViewHolder holder, int position) {
        Visite visite = visiteList.get(position);

        // Nettoyer la date
        String date = visite.getDateVisite();
        if (date != null && date.contains("T")) {
            date = date.split("T")[0];
        }

        // Extraire libellé du motif
        String motifLibelle = "";
        if (visite.getMotif() instanceof Map) {
            Object libelleObj = ((Map<?, ?>) visite.getMotif()).get("libelle");
            if (libelleObj != null) {
                motifLibelle = libelleObj.toString();
            }
        }

        holder.dateText.setText(date);
        holder.motifText.setText("Motif : " + motifLibelle);
        holder.commentaireText.setText(visite.getCommentaire());

        // 👇 Gérer le clic sur une visite
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VisiteDetailActivity.class);
            intent.putExtra("visite", visite);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return visiteList.size();
    }

    public static class VisiteViewHolder extends RecyclerView.ViewHolder {
        TextView dateText, motifText, commentaireText;

        public VisiteViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.date_visite);
            motifText = itemView.findViewById(R.id.motif_visite);
            commentaireText = itemView.findViewById(R.id.commentaire_visite);
        }
    }
}