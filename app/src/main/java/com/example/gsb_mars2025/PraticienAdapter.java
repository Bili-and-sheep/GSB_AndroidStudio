package com.example.gsb_mars2025;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PraticienAdapter extends RecyclerView.Adapter<PraticienAdapter.ViewHolder> {
    private List<Praticien> list;

    public PraticienAdapter(List<Praticien> list) {
        this.list = list;
    }

    public void updateData(List<Praticien> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_praticien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Praticien p = list.get(position);
        holder.tvNom.setText(p.getNom() + " " + p.getPrenom());
        holder.tvSpecialite.setText(p.getTel());  // ou autre champ
        holder.tvVille.setText(p.getVille());
    }

    @Override public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNom, tvSpecialite, tvVille;
        ViewHolder(View itemView) {
            super(itemView);
            tvNom        = itemView.findViewById(R.id.tvNom);
            tvSpecialite = itemView.findViewById(R.id.tvSpecialite);
            tvVille      = itemView.findViewById(R.id.tvVille);
        }
    }
}