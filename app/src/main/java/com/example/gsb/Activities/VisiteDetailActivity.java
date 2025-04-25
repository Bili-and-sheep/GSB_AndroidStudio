package com.example.gsb.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Visite;
import com.example.gsb.R;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiteDetailActivity extends AppCompatActivity {

    private TextView textDate, textMotif, textCommentaire, textVisiteur, textPraticien;
    private Button btnModifier, btnSupprimer;
    private Visite visite;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visite_detail);

        textDate = findViewById(R.id.text_date);
        textMotif = findViewById(R.id.text_motif);
        textCommentaire = findViewById(R.id.text_commentaire);
        textVisiteur = findViewById(R.id.text_visiteur);
        textPraticien = findViewById(R.id.text_praticien);
        btnModifier = findViewById(R.id.btn_modifier);
        btnSupprimer = findViewById(R.id.btn_supprimer);

        visite = (Visite) getIntent().getSerializableExtra("visite");

        SharedPreferences prefs = getSharedPreferences("gsb_prefs", MODE_PRIVATE);
        token = prefs.getString("token", null);

        if (visite != null) {
            refreshUI();
        }

        btnSupprimer.setOnClickListener(v -> confirmAndDeleteVisite());

        btnModifier.setOnClickListener(v -> {
            Intent intent = new Intent(this, UpdateVisiteActivity.class);
            intent.putExtra("visite", visite);
            startActivityForResult(intent, 100); // ✅ Lance avec attente de retour
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK) {
            reloadVisite(); // ✅ Recharge depuis l'API
        }
    }

    private void reloadVisite() {
        ApiGsbVisites api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
        api.getVisiteById("Bearer " + token, visite.getId()).enqueue(new Callback<Visite>() {
            @Override
            public void onResponse(Call<Visite> call, Response<Visite> response) {
                if (response.isSuccessful()) {
                    visite = response.body();
                    refreshUI(); // ✅ Mise à jour de l’UI
                }
            }

            @Override
            public void onFailure(Call<Visite> call, Throwable t) {
                Log.e("GSB_DEBUG", "Erreur chargement visite : " + t.getMessage());
            }
        });
    }

    private void refreshUI() {
        if (visite == null) return;

        String date = visite.getDateVisite();
        if (date != null && date.contains("T")) {
            date = date.split("T")[0];
        }

        textDate.setText("Date : " + date);
        textCommentaire.setText("Commentaire : " + visite.getCommentaire());

        if (visite.getMotif() instanceof Map) {
            Object libelle = ((Map<?, ?>) visite.getMotif()).get("libelle");
            textMotif.setText("Motif : " + (libelle != null ? libelle.toString() : ""));
        }

        if (visite.getVisiteur() instanceof Map) {
            Object nom = ((Map<?, ?>) visite.getVisiteur()).get("nom");
            Object prenom = ((Map<?, ?>) visite.getVisiteur()).get("prenom");
            textVisiteur.setText("Visiteur : " + prenom + " " + nom);
        }

        if (visite.getPraticien() instanceof Map) {
            Object nom = ((Map<?, ?>) visite.getPraticien()).get("nom");
            Object prenom = ((Map<?, ?>) visite.getPraticien()).get("prenom");
            textPraticien.setText("Praticien : " + prenom + " " + nom);
        }
    }

    private void confirmAndDeleteVisite() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Supprimer cette visite ?")
                .setPositiveButton("Oui", (dialog, which) -> deleteVisite())
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void deleteVisite() {
        if (token == null || visite == null || visite.getId() == null) {
            Toast.makeText(this, "Erreur : Token ou ID manquant", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiGsbVisites api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
        api.deleteVisite("Bearer " + token, visite.getId()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(VisiteDetailActivity.this, "Visite supprimée ✅", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(VisiteDetailActivity.this, "Erreur suppression : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(VisiteDetailActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}