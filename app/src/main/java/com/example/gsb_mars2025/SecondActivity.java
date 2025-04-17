package com.example.gsb_mars2025;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondActivity extends AppCompatActivity {
    private RecyclerView rvPortefeuille;
    private PraticienAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        rvPortefeuille = findViewById(R.id.rvPortefeuille);
        rvPortefeuille.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PraticienAdapter(new ArrayList<>());
        rvPortefeuille.setAdapter(adapter);

        String token = getIntent().getStringExtra("TOKEN");
        String bearer = "Bearer " + token;
        SaperliExpressInterface api =
                RetrofitClientInstance.getRetrofitInstance()
                        .create(SaperliExpressInterface.class);
        api.getPortefeuille(bearer).enqueue(new Callback<List<Praticien>>() {
            @Override
            public void onResponse(Call<List<Praticien>> call, Response<List<Praticien>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.updateData(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Praticien>> call, Throwable t) {
                // gérer l'erreur
            }
        });

        Button backButton = findViewById(R.id.BackButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}