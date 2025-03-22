package com.example.gsb_mars2025;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("visiteurId")
    private String visiteurId;

    @SerializedName("token")
    private String token;

    public String getVisiteurId() { return visiteurId; }
    public String getToken() { return token; }
}
