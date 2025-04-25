package com.example.gsb.API;

import com.example.gsb.Model.Motif;
import com.example.gsb.Model.Praticien;
import com.example.gsb.Model.Visite;
import com.example.gsb.Model.Visiteur;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.PUT;

public interface ApiGsbVisites {

    @POST("visiteurs/login")
    Call<Visiteur> loginVisiteur(@Body Map<String, String> body);

    @GET("visiteurs/{id}")
    Call<Visiteur> getVisiteurById(@Header("Authorization") String token, @Path("id") String id);

    @GET("praticiens/byVisiteur/{id}")
    Call<List<Praticien>> getPraticiensByVisiteur(@Path("id") String id);

    @GET("visites/vbyp")
    Call<List<Visite>> getVisitesByPraticien(@Query("praticienId") String praticienId);

    @GET("motifs")
    Call<List<Motif>> getMotifs();

    @POST("visites")
    Call<Visite> createVisite(@Header("Authorization") String token, @Body Visite visite);

    @GET("visites/{id}")
    Call<Visite> getVisiteById(@Header("Authorization") String token, @Path("id") String id);

    @DELETE("visites/deletevisite/{id}")
    Call<Void> deleteVisite(@Header("Authorization") String token, @Path("id") String id);

    @PUT("visites/updateVisite/{id}")
    Call<Visite> updateVisite(@Header("Authorization") String token, @Path("id") String id, @Body Visite visite);

}
