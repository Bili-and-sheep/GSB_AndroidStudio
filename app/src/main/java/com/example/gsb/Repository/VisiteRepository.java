package com.example.gsb.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Visite;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiteRepository {

    private final ApiGsbVisites api;

    public VisiteRepository() {
        api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
    }

    public LiveData<List<Visite>> getVisitesByPraticien(String praticienId) {
        MutableLiveData<List<Visite>> data = new MutableLiveData<>();

        api.getVisitesByPraticien(praticienId).enqueue(new Callback<List<Visite>>() {
            @Override
            public void onResponse(Call<List<Visite>> call, Response<List<Visite>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Visite>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}