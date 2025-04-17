package com.example.gsb_mars2025;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Praticien {
    @SerializedName("_id")
    private String id;
    @SerializedName("nom")
    private String nom;
    @SerializedName("prenom")
    private String prenom;
    @SerializedName("tel")
    private String tel;
    @SerializedName("email")
    private String email;
    @SerializedName("rue")
    private String rue;
    @SerializedName("code_postal")
    private String codePostal;
    @SerializedName("ville")
    private String ville;
    @SerializedName("visites")
    private List<String> visites;

    // Getters
    public String getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getTel() { return tel; }
    public String getEmail() { return email; }
    public String getRue() { return rue; }
    public String getCodePostal() { return codePostal; }
    public String getVille() { return ville; }
    public List<String> getVisites() { return visites; }
}