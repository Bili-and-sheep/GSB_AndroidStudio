package com.example.gsb.Activities;

import android.content.SharedPreferences;
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
import com.example.gsb.Model.Visite;
import com.example.gsb.R;
import com.example.gsb.ViewModel.MotifViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateVisiteActivity extends AppCompatActivity {

    private EditText editDate, editCommentaire;
    private Spinner spinnerMotif;
    private Button btnValider;
    private MotifViewModel motifViewModel;

    private Visite visite;
    private List<Motif> motifs = new ArrayList<>();
    private List<String> motifLibelles = new ArrayList<>();
    private String token;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_visite);

        editDate = findViewById(R.id.edit_date);
        editCommentaire = findViewById(R.id.edit_commentaire);
        spinnerMotif = findViewById(R.id.spinner_motif);
        btnValider = findViewById(R.id.btn_valider);

        visite = (Visite) getIntent().getSerializableExtra("visite");

        SharedPreferences prefs = getSharedPreferences("gsb_prefs", MODE_PRIVATE);
        token = prefs.getString("token", null);

        motifViewModel = new ViewModelProvider(this).get(MotifViewModel.class);
        motifViewModel.getMotifs().observe(this, loadedMotifs -> {
            if (loadedMotifs != null) {
                motifs.clear();
                motifLibelles.clear();
                motifs.addAll(loadedMotifs);
                for (Motif m : motifs) {
                    motifLibelles.add(m.getLibelle());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, motifLibelles);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMotif.setAdapter(adapter);

                // Pré-sélectionner le motif
                for (int i = 0; i < motifs.size(); i++) {
                    if (visite.getMotif() instanceof java.util.Map) {
                        String currentId = ((java.util.Map<?, ?>) visite.getMotif()).get("_id").toString();
                        if (motifs.get(i).getId().equals(currentId)) {
                            spinnerMotif.setSelection(i);
                            break;
                        }
                    }
                }
            }
        });

        if (visite != null) {
            editDate.setText(visite.getDateVisite().split("T")[0]);
            editCommentaire.setText(visite.getCommentaire());
        }

        btnValider.setOnClickListener(v -> {
            String date = editDate.getText().toString().trim() + "T10:00:00Z";
            String commentaire = editCommentaire.getText().toString().trim();
            Motif selectedMotif = motifs.get(spinnerMotif.getSelectedItemPosition());

            Visite updatedVisite = new Visite();
            updatedVisite.setDateVisite(date);
            updatedVisite.setCommentaire(commentaire);
            updatedVisite.setMotif(selectedMotif.getId());

            ApiGsbVisites api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
            api.updateVisite("Bearer " + token, visite.getId(), updatedVisite).enqueue(new Callback<Visite>() {
                @Override
                public void onResponse(Call<Visite> call, Response<Visite> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(UpdateVisiteActivity.this, "Visite modifiée ✅", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK); // ✅ Indique que la modification est un succès
                        finish();
                    } else {
                        Toast.makeText(UpdateVisiteActivity.this, "Erreur update : " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Visite> call, Throwable t) {
                    Toast.makeText(UpdateVisiteActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}