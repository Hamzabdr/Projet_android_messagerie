package com.example.mbdse.firstapp;

public class Person {
    private String nom;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    private String prenom;

    public Person(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Person() {
    }
}
