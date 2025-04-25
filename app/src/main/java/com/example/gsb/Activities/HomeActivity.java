package com.example.gsb.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visiteur;
import com.example.gsb.R;
import com.example.gsb.ViewModel.VisiteurViewModel;
import com.example.gsb.adapters.PraticienAdapter;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private Visiteur visiteur;
    private VisiteurViewModel visiteurViewModel;
    private TextView textViewHomeWelcome;
    private RecyclerView recyclerViewPraticiens;
    private Button btnNewPracticien;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Récupérer le visiteur passé depuis MainActivity
        visiteur = (Visiteur) getIntent().getSerializableExtra("visiteur");

        textViewHomeWelcome = findViewById(R.id.textViewBienvenue);
        recyclerViewPraticiens = findViewById(R.id.recyclerViewPraticiens);
        recyclerViewPraticiens.setLayoutManager(new LinearLayoutManager(this));
        btnNewPracticien = findViewById(R.id.btn_praticiens);
        btnLogout = findViewById(R.id.btn_logout);


        if (visiteur != null) {
            textViewHomeWelcome.setText("Bienvenue " + visiteur.getPrenom() + " " + visiteur.getNom());

            Log.d("HomeActivity", "Visiteur ID: " + visiteur.getVisiteurId());

            visiteurViewModel = new ViewModelProvider(this).get(VisiteurViewModel.class);

            // Charger les praticiens
            visiteurViewModel.getPraticiens(visiteur.getVisiteurId()).observe(this, this::setupRecyclerView);
        }

        btnNewPracticien.setOnClickListener(v -> {
            Intent intentHome = new Intent(HomeActivity.this, CreatePraticienActivity.class);
            startActivity(intentHome);
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("gsb_prefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear(); // 🔥 Efface tout (token + visiteur)
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class); // retourne vers login
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // ⚡ clean toutes les anciennes activités
            startActivity(intent);
            finish();
        });
    }

    private void setupRecyclerView(List<Praticien> praticiens) {
        PraticienAdapter adapter = new PraticienAdapter(this, praticiens);
        recyclerViewPraticiens.setAdapter(adapter);
    }
}