package com.example.gsb.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.Model.Visiteur;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private ApiGsbVisites service;

    public AuthRepository(ApiGsbVisites service) {
        this.service = service;
    }

    public LiveData<Visiteur> login(String email, String password) {
        MutableLiveData<Visiteur> loginResult = new MutableLiveData<>();

        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        Call<Visiteur> call = service.loginVisiteur(loginData);
        call.enqueue(new Callback<Visiteur>() {
            @Override
            public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginResult.setValue(response.body());
                } else {
                    loginResult.setValue(null);
                }
            }
            @Override
            public void onFailure(Call<Visiteur> call, Throwable t) {
                loginResult.setValue(null);
            }
        });

        return loginResult;
    }
}
