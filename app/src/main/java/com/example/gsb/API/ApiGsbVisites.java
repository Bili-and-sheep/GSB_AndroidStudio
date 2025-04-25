package com.example.gsb.API;

import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visite;
import com.example.gsb.Model.Visiteur;

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

    @GET("visites/byPraticien/{id}")
    Call<List<Visite>> getVisitesByPraticien(@Path("id") String praticienId);
}
