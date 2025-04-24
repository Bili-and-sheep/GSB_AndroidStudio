package com.example.gsb.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visiteur;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiteurRepository {
    private final ApiGsbVisites api;

    public VisiteurRepository() {
        api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
    }

    public LiveData<List<Praticien>> getPraticiens(String visiteurId) {
        MutableLiveData<List<Praticien>> data = new MutableLiveData<>();

        api.getPraticiensByVisiteur(visiteurId).enqueue(new Callback<List<Praticien>>() {
            @Override
            public void onResponse(Call<List<Praticien>> call, Response<List<Praticien>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Praticien>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
    public LiveData<Visiteur> getVisiteurById(String token, String id) {
        MutableLiveData<Visiteur> data = new MutableLiveData<>();

        api.getVisiteurById(token, id).enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}