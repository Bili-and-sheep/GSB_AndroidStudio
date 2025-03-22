package com.example.gsb_mars2025;

import retrofit2.http.GET;

public interface OpenWeatherServices {
    @GET("weather?q=London&appid=e075a0b59517e88cc46940bb262add13")
    Call<Visiteur> getVisiteur();
}