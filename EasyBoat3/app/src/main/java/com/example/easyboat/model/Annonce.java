package com.example.easyboat.model;

import com.example.easyboat.R;

public class Annonce {
    private String resume;
    private String nom;
    private String nbPlace;
    private String montage;
    private String date;
    private String annee;
    private String description;
    private String clubnom;
    private String clubtel;
    private String clubmail;
    private String id;

    public Annonce(String resume, String nom, String nbPlace, String montage, String date, String annee, String description, String clubnom, String clubmail, String clubtel, String id) {
        this.resume= resume;
        this.nom = nom;
        this.nbPlace = nbPlace;
        this.montage = montage;
        this.date = date;
        this.annee = annee;
        this.description = description;
        this.clubmail = clubmail;
        this.clubnom=clubnom;
        this.clubtel=clubtel;
        this.id=id;
    }

    public String getClubnom() {
        return "Nom du club : " + clubnom;
    }

    public void setClubnom(String clubnom) {
        this.clubnom = clubnom;
    }

    public String getClubtel() {
        return "Numéro de téléphone du club : "+clubtel;
    }

    public void setClubtel(String clubtel) {
        this.clubtel = clubtel;
    }

    public String getClubmail() {
        return "Adresse mail du club : " + clubmail;
    }

    public void setClubmail(String clubmail) {
        this.clubmail = clubmail;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getNom() {
        return "Marque : " + nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNbPlace() {
        return "Nombre de place : " + nbPlace;
    }

    public void setNbPlace(String nbPlace) {
        this.nbPlace = nbPlace;
    }

    public String getMontage() {
        return "Montage : " + montage;
    }

    public void setMontage(String montage) {
        this.montage = montage;
    }

    public String getDate() {
        return "Date de disponibilité : " + date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAnnee() {
        return "Année d'achat : " + annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getDescription() {
        return "Description complète : " + description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


