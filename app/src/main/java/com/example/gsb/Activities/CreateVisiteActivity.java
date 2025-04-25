package com.example.gsb.Activities;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Motif;
import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visite;
import com.example.gsb.R;
import com.example.gsb.ViewModel.MotifViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateVisiteActivity extends AppCompatActivity {

    private EditText editDate, editCommentaire;
    private Spinner spinnerMotif;
    private Button btnValider;

    private List<Motif> motifs = new ArrayList<>();
    private List<String> motifLibelles = new ArrayList<>();
    private MotifViewModel motifViewModel;

    private Praticien praticien;
    private String visiteurId;
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_visite);

        editDate = findViewById(R.id.edit_date);
        editCommentaire = findViewById(R.id.edit_commentaire);
        spinnerMotif = findViewById(R.id.spinner_motif);
        btnValider = findViewById(R.id.btn_valider);

        // 🔁 Récupération du praticien et du token depuis l’Intent
        praticien = (Praticien) getIntent().getSerializableExtra("praticien");
        token = getIntent().getStringExtra("token");

        if (token != null) {
            visiteurId = decodeTokenForVisiteurId(token);
            Log.d("GSB_DEBUG", "Visiteur ID extrait du token : " + visiteurId);
        }

        motifViewModel = new ViewModelProvider(this).get(MotifViewModel.class);
        motifViewModel.getMotifs().observe(this, loadedMotifs -> {
            if (loadedMotifs != null) {
                motifs.clear();
                motifLibelles.clear();
                motifs.addAll(loadedMotifs);
                for (Motif m : loadedMotifs) {
                    motifLibelles.add(m.getLibelle());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, motifLibelles);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMotif.setAdapter(adapter);
            } else {
                Toast.makeText(this, "Erreur de chargement des motifs", Toast.LENGTH_SHORT).show();
            }
        });

        btnValider.setOnClickListener(v -> {
            String date = editDate.getText().toString().trim();
            String commentaire = editCommentaire.getText().toString().trim();
            Motif selectedMotif = motifs.get(spinnerMotif.getSelectedItemPosition());

            if (date.isEmpty() || commentaire.isEmpty()) {
                Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
                return;
            }

            String dateFormatted = date + "T10:00:00Z";

            Visite visite = new Visite();
            visite.setDateVisite(dateFormatted);
            visite.setCommentaire(commentaire);
            visite.setMotif(selectedMotif.getId());
            visite.setPraticien(praticien.get_id());
            visite.setVisiteur(visiteurId);

            ApiGsbVisites api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
            api.createVisite("Bearer " + token, visite).enqueue(new Callback<Visite>() {
                @Override
                public void onResponse(Call<Visite> call, Response<Visite> response) {
                    if (response.isSuccessful()) {
                        Log.d("GSB_DEBUG", "Visite créée : " + new Gson().toJson(response.body()));
                        Toast.makeText(CreateVisiteActivity.this, "Visite créée avec succès ✅", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CreateVisiteActivity.this, "Erreur HTTP " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Visite> call, Throwable t) {
                    Toast.makeText(CreateVisiteActivity.this, "Échec requête : " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }

    private String decodeTokenForVisiteurId(String jwt) {
        try {
            String[] parts = jwt.split("\\.");
            byte[] payloadBytes = Base64.decode(parts[1], Base64.URL_SAFE);
            String payloadJson = new String(payloadBytes);
            JsonObject payload = new Gson().fromJson(payloadJson, JsonObject.class);
            return payload.get("visiteurId").getAsString();
        } catch (Exception e) {
            Log.e("GSB_DEBUG", "Erreur décodage JWT : " + e.getMessage());
            return null;
        }
    }
}