package com.example.gsb.Model;

import java.io.Serializable;

public class Praticien implements Serializable {
    private String _id;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String rue;
    private String code_postal;
    private String ville;

    public String get_id() {
        return _id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public String getRue() {
        return rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public String getVille() {
        return ville;
    }

    // 🔥 SETTERS AJOUTÉS

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}