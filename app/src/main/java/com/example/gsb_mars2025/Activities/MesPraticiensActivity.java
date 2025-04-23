package com.example.gsb_mars2025.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_mars2025.Model.Praticien;
import com.example.gsb_mars2025.R;
import com.example.gsb_mars2025.ViewModel.VisiteurViewModel;
import com.example.gsb_mars2025.adapters.PraticienAdapter;

import java.util.List;

public class MesPraticiensActivity extends AppCompatActivity {

    private VisiteurViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_praticiens);

        recyclerView = findViewById(R.id.recyclerViewPraticiens);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String visiteurId = getIntent().getStringExtra("visiteurId");
        Log.d("DEBUG", "visiteurId reçu : " + visiteurId); // 🔍 Vérifie qu'on reçoit bien l'ID

        viewModel = new ViewModelProvider(this).get(VisiteurViewModel.class);

        if (visiteurId != null) {
            viewModel.getPraticiens(visiteurId).observe(this, praticiens -> {
                if (praticiens != null && !praticiens.isEmpty()) {
                    Log.d("DEBUG", "Nombre de praticiens reçus : " + praticiens.size());
                    PraticienAdapter adapter = new PraticienAdapter(praticiens);
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.e("API", "Liste vide ou null reçue de l'API");
                }
            });
        } else {
            Log.e("ERROR", "visiteurId est null, impossible d’appeler l’API");
        }
    }
}