package com.example.gsb.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gsb.Model.Visiteur;
import com.example.gsb.ViewModel.VisiteurViewModel;
import com.example.gsb.databinding.ActivityHomeBinding;
import com.example.gsb.databinding.ActivityMainBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private Visiteur visiteur;
    private VisiteurViewModel visiteurViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonMesPraticiens.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MesPraticiensActivity.class);
            intent.putExtra("visiteurId", visiteur.getVisiteurId());  // 👈 assure-toi que c’est bien ça
            startActivity(intent);
        });

        visiteur = (Visiteur) getIntent().getSerializableExtra("visiteur");

        visiteurViewModel = new ViewModelProvider(this).get(VisiteurViewModel.class);

        visiteurViewModel.getVisiteurById("Bearer " + visiteur.getToken(), visiteur.getVisiteurId())
                .observe(this, visiteur -> {
                    if (visiteur != null) {
                        binding.textViewHomeWelcome.setText("Bienvenue " + visiteur.getPrenom() + " " + visiteur.getNom());
                    } else {
                        Toast.makeText(this, "Erreur de chargement du visiteur", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}