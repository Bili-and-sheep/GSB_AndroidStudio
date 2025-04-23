package com.example.gsb_mars2025.API;

import com.example.gsb_mars2025.Model.Praticien;
import com.example.gsb_mars2025.Model.Visiteur;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiGsbVisites {

    @POST("visiteurs/login")
    Call<Visiteur> loginVisiteur(@Body Map<String, String> body);

    @GET("visiteurs/{id}")
    Call<Visiteur> getVisiteurById(@Header("Authorization") String token, @Path("id") String id);

    @GET("praticiens/byVisiteur/{id}")
    Call<List<Praticien>> getPraticiensByVisiteur(@Path("id") String id);


}
