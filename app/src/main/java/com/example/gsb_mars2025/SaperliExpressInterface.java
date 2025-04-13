package com.example.gsb_mars2025;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SaperliExpressInterface {

    @POST("visiteurs/login") // Assurez-vous que cette route correspond bien à ton API
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("/praticiens")
    Call<LoginPracticienResponse> login(@Body LoginPracticienRequest loginPracticienRequest);

    @GET("/motifs")
    Call<VisitesResponse> login(@Body VisitesRequest visitesRequest);

    @GET("/motifs")
    Call<LoginPracticienResponse> login(@Body LoginPracticienRequest loginPracticienRequest);
}
