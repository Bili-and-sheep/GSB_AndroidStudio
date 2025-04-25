package com.example.gsb.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gsb.Activities.PraticienDetailsActivity;
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
        holder.nomTextView.setText(praticien.getNom() + " " + praticien.getPrenom());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PraticienDetailsActivity.class);
            intent.putExtra("praticien", praticien);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return praticiens.size();
    }

    public static class PraticienViewHolder extends RecyclerView.ViewHolder {
        TextView nomTextView;

        public PraticienViewHolder(View itemView) {
            super(itemView);
            nomTextView = itemView.findViewById(R.id.textViewNom); // à adapter selon ton layout
        }
    }
}