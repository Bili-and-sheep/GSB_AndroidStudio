package com.example.gsb.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Visiteur;
import com.example.gsb.Repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private AuthRepository authRepository;


    public AuthViewModel() {
        ApiGsbVisites service = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
        this.authRepository = new AuthRepository(service);
    }

     public LiveData<Visiteur>  login(String email, String password) {
        return authRepository.login(email, password);
    }
}

