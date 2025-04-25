package com.example.gsb.Model;

import java.io.Serializable;

public class Praticien implements Serializable {
    private String _id;
    private String nom;
    private String prenom;
    private String ville;
    private String email;

    public String get_id() {
        return _id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getVille() {
        return ville;
    }

    public String getEmail() {
        return email;
    }
}