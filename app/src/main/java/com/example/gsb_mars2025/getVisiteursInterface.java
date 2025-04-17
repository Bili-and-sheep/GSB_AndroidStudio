package com.example.gsb_mars2025;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface getVisiteursInterface {
    @POST("visiteurs/login") // Assurez-vous que cette route correspond bien à ton API
    Call<LoginResponse> login(@Body LoginRequest loginRequest);


}
