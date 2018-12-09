package com.example.fares.tpsqlite;

public class Contact {

    private  int id;
    private String nom;

    public Contact(String nom, String number) {
        this.nom = nom;
        this.number = number;
    }

    private String number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Contact(int id, String nom, String number) {

        this.id = id;
        this.nom = nom;
        this.number = number;
    }
}
