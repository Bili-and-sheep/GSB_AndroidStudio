package com.example.gsb_mars2025.ViewModel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gsb_mars2025.API.ApiGsbVisites;
import com.example.gsb_mars2025.API.RetrofitClientInstance;
import com.example.gsb_mars2025.Model.Visiteur;
import com.example.gsb_mars2025.Repository.AuthRepository;

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

