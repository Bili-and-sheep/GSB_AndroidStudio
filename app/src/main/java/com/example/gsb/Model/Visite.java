package com.example.gsb.Model;

import java.io.Serializable;

public class Visite implements Serializable {
    private String _id;
    private String date_visite;
    private String commentaire;
    private Object visiteur;
    private Object praticien;
    private Object motif;

    public String getId() {
        return _id;
    }

    public String getDateVisite() {
        return date_visite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public Object getVisiteur() {
        return visiteur;
    }

    public Object getPraticien() {
        return praticien;
    }

    public Object getMotif() {
        return motif;
    }

    public void setDateVisite(String date_visite) {
        this.date_visite = date_visite;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public void setVisiteur(Object visiteur) {
        this.visiteur = visiteur;
    }

    public void setPraticien(Object praticien) {
        this.praticien = praticien;
    }

    public void setMotif(Object motif) {
        this.motif = motif;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "id='" + _id + '\'' +
                ", date='" + date_visite + '\'' +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}