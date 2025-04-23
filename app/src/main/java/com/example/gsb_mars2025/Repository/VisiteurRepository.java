package com.example.gsb_mars2025.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb_mars2025.API.ApiGsbVisites;
import com.example.gsb_mars2025.Model.Visiteur;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiteurRepository {
    private ApiGsbVisites service;

    public VisiteurRepository(ApiGsbVisites service) {
        this.service = service;
    }

    public LiveData<Visiteur> getVisiteurById(String token, String id) {
        MutableLiveData<Visiteur> visiteurLiveData = new MutableLiveData<>();

        service.getVisiteurById(token, id).enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    visiteurLiveData.setValue(response.body());
                } else {
                    visiteurLiveData.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                visiteurLiveData.setValue(null);
            }
        });

        return visiteurLiveData;
    }
}