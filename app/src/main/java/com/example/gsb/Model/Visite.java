package com.example.gsb.Model;

public class Visite {
    private String _id;
    private String date_visite;
    private String commentaire;
    private Object visiteur;   // objet JSON
    private Object praticien;  // objet JSON aussi !
    private Object motif;      // objet JSON aussi

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

    @Override
    public String toString() {
        return "Visite{" +
                "id='" + _id + '\'' +
                ", date='" + date_visite + '\'' +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}