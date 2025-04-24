package com.example.gsb.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visiteur implements Serializable {

    private String visiteurId;
    private String token;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    @SerializedName("date_embauche")
    private String dateEmbauche;

       public String getVisiteurId() {
        return visiteurId;
    }

    public String getToken() {
        return token;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }
}
