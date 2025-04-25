package com.example.gsb.Activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gsb.Model.Visite;
import com.example.gsb.R;
import java.util.Map;

public class VisiteDetailActivity extends AppCompatActivity {

    private TextView textDate, textMotif, textCommentaire, textVisiteur, textPraticien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visite_detail);

        textDate = findViewById(R.id.text_date);
        textMotif = findViewById(R.id.text_motif);
        textCommentaire = findViewById(R.id.text_commentaire);
        textVisiteur = findViewById(R.id.text_visiteur);
        textPraticien = findViewById(R.id.text_praticien);

        Visite visite = (Visite) getIntent().getSerializableExtra("visite");

        if (visite != null) {
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
    }
}