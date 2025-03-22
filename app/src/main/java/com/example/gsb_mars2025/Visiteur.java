package com.example.gsb_mars2025;

import com.google.gson.annotations.SerializedName;

public class Visiteur {
    @SerializedName("id")
    private String id;

    @SerializedName("nom")
    private String nom;

    @SerializedName("prenom")
    private String prenom;

    @SerializedName("email")
    private String email;

    // Getters et setters
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
}