package com.example.gsb_mars2025.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_mars2025.Model.Praticien;
import com.example.gsb_mars2025.R;

import java.util.List;

public class PraticienAdapter extends RecyclerView.Adapter<PraticienAdapter.ViewHolder> {

    private List<Praticien> praticiens;

    public PraticienAdapter(List<Praticien> praticiens) {
        this.praticiens = praticiens;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nom, ville;

        public ViewHolder(View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.textViewNomPraticien);
            ville = itemView.findViewById(R.id.textViewVillePraticien);
        }
    }

    @Override
    public PraticienAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_praticien, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PraticienAdapter.ViewHolder holder, int position) {
        Praticien p = praticiens.get(position);
        holder.nom.setText(p.getPrenom() + " " + p.getNom());
        holder.ville.setText(p.getVille());
    }

    @Override
    public int getItemCount() {
        return praticiens.size();
    }
}