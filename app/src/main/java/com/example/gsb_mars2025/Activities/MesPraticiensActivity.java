package com.example.gsb_mars2025.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gsb_mars2025.Model.Praticien;
import com.example.gsb_mars2025.R;
import com.example.gsb_mars2025.API.ApiGsbVisites;
import com.example.gsb_mars2025.API.RetrofitClientInstance;
import com.example.gsb_mars2025.adapters.PraticienAdapter;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesPraticiensActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PraticienAdapter adapter;
    private String visiteurId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_praticiens);

        visiteurId = getIntent().getStringExtra("visiteurId");
        Log.d("DEBUG", "Visiteur ID: " + visiteurId);
        recyclerView = findViewById(R.id.recyclerViewPraticiens);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadPraticiens();
    }

    private void loadPraticiens() {
        ApiGsbVisites service = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
        Call<List<Praticien>> call = service.getPraticiensByVisiteur(visiteurId);

        call.enqueue(new Callback<List<Praticien>>() {
            @Override
            public void onResponse(Call<List<Praticien>> call, Response<List<Praticien>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new PraticienAdapter(response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MesPraticiensActivity.this, "Aucun praticien trouvé", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Praticien>> call, Throwable t) {
                Log.e("API", "Erreur : " + t.getMessage());
                Toast.makeText(MesPraticiensActivity.this, "Erreur réseau", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
