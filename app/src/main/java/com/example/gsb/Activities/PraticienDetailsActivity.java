package com.example.gsb.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visite;
import com.example.gsb.R;
import com.example.gsb.ViewModel.VisiteViewModel;
import com.example.gsb.adapters.VisiteAdapter;

import java.util.List;

public class PraticienDetailsActivity extends AppCompatActivity {

    private TextView nomPrenomTextView, emailTextView;
    private RecyclerView recyclerView;
    private Button btnNouvelleVisite;
    private VisiteAdapter adapter;
    private VisiteViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_praticien_details);

        nomPrenomTextView = findViewById(R.id.nom_prenom);
        emailTextView = findViewById(R.id.email_praticien);
        recyclerView = findViewById(R.id.recycler_visites);
        btnNouvelleVisite = findViewById(R.id.btn_nouvelle_visite);

        Praticien praticien = (Praticien) getIntent().getSerializableExtra("praticien");

        if (praticien != null) {
            nomPrenomTextView.setText(praticien.getNom() + " " + praticien.getPrenom());
            emailTextView.setText(praticien.getEmail());

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new VisiteAdapter(new java.util.ArrayList<>());
            recyclerView.setAdapter(adapter);

            viewModel = new ViewModelProvider(this).get(VisiteViewModel.class);
            viewModel.getVisitesByPraticien(praticien.get_id()).observe(this, visites -> {
                if (visites != null) {
                    adapter = new VisiteAdapter(visites);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(this, "Erreur de chargement des visites", Toast.LENGTH_SHORT).show();
                }
            });
        }

        btnNouvelleVisite.setOnClickListener(v -> {
            // TODO: ouvrir l'activité de création de visite
        });
    }
}