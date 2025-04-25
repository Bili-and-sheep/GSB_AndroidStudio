package com.example.gsb.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gsb.Model.Visite;
import com.example.gsb.R;
import java.util.List;

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
        holder.dateText.setText(visite.getDateVisite());
        holder.motifText.setText("Motif ID: " + visite.getMotif());
        holder.commentaireText.setText(visite.getCommentaire());
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