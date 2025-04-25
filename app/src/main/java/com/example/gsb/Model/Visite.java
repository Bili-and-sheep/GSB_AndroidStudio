package com.example.gsb.Model;

public class Visite {
    private String _id;
    private String date_visite;
    private String commentaire;
    private String visiteur;
    private String praticien;
    private String motif;

    public String getId() {
        return _id;
    }

    public String getDateVisite() {
        return date_visite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getVisiteur() {
        return visiteur;
    }

    public String getPraticien() {
        return praticien;
    }

    public String getMotif() {
        return motif;
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