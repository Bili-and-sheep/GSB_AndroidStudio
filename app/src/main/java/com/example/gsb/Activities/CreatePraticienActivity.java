package com.example.gsb.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Praticien;
import com.example.gsb.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePraticienActivity extends AppCompatActivity {

    private EditText editNom, editPrenom, editTel, editEmail, editRue, editCodePostal, editVille;
    private Button btnValider;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_praticien);

        editNom = findViewById(R.id.edit_nom);
        editPrenom = findViewById(R.id.edit_prenom);
        editTel = findViewById(R.id.edit_tel);
        editEmail = findViewById(R.id.edit_email);
        editRue = findViewById(R.id.edit_rue);
        editCodePostal = findViewById(R.id.edit_code_postal);
        editVille = findViewById(R.id.edit_ville);
        btnValider = findViewById(R.id.btn_valider);

        SharedPreferences prefs = getSharedPreferences("gsb_prefs", MODE_PRIVATE);
        token = prefs.getString("token", null);

        btnValider.setOnClickListener(v -> createPraticien());
    }

    private void createPraticien() {
        String nom = editNom.getText().toString().trim();
        String prenom = editPrenom.getText().toString().trim();
        String tel = editTel.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String rue = editRue.getText().toString().trim();
        String codePostal = editCodePostal.getText().toString().trim();
        String ville = editVille.getText().toString().trim();

        if (nom.isEmpty() || prenom.isEmpty() || tel.isEmpty() || email.isEmpty() || rue.isEmpty() || codePostal.isEmpty() || ville.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        Praticien praticien = new Praticien();
        praticien.setNom(nom);
        praticien.setPrenom(prenom);
        praticien.setTel(tel);
        praticien.setEmail(email);
        praticien.setRue(rue);
        praticien.setCode_postal(codePostal);
        praticien.setVille(ville);

        ApiGsbVisites api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
        api.createPraticien("Bearer " + token, praticien).enqueue(new Callback<Praticien>() {
            @Override
            public void onResponse(Call<Praticien> call, Response<Praticien> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreatePraticienActivity.this, "Praticien créé ✅", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CreatePraticienActivity.this, "Erreur création : " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Praticien> call, Throwable t) {
                Toast.makeText(CreatePraticienActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}