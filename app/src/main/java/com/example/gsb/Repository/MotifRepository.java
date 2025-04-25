package com.example.gsb.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gsb.API.ApiGsbVisites;
import com.example.gsb.API.RetrofitClientInstance;
import com.example.gsb.Model.Motif;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotifRepository {

    private final ApiGsbVisites api;

    public MotifRepository() {
        api = RetrofitClientInstance.getRetrofitInstance().create(ApiGsbVisites.class);
    }

    public LiveData<List<Motif>> getMotifs() {
        MutableLiveData<List<Motif>> data = new MutableLiveData<>();

        api.getMotifs().enqueue(new Callback<List<Motif>>() {
            @Override
            public void onResponse(Call<List<Motif>> call, Response<List<Motif>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Motif>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}