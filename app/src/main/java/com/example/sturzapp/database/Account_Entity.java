package com.example.sturzapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "accounts")
public class Account_Entity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String zipCity;

    private String notfallkontaktName;
    private String notfallkontaktAddresse;
    private String notfallkontaktPostleitzahl;
    private String notfallkontaktEmail;

    public Account_Entity(String email, String password, String firstName, String lastName, String address, String zipCity, String notfallkontaktName, String notfallkontaktAddresse, String notfallkontaktPostleitzahl, String notfallkontaktEmail) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.zipCity = zipCity;
        this.notfallkontaktName = notfallkontaktName;
        this.notfallkontaktAddresse = notfallkontaktAddresse;
        this.notfallkontaktPostleitzahl = notfallkontaktPostleitzahl;
        this.notfallkontaktEmail = notfallkontaktEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCity() {
        return zipCity;
    }

    public void setZipCity(String zipCity) {
        this.zipCity = zipCity;
    }

    public String getNotfallkontaktName() {
        return notfallkontaktName;
    }

    public void setNotfallkontaktName(String notfallkontaktName) {
        this.notfallkontaktName = notfallkontaktName;
    }

    public String getNotfallkontaktAddresse() {
        return notfallkontaktAddresse;
    }

    public void setNotfallkontaktAddresse(String notfallkontaktAddresse) {
        this.notfallkontaktAddresse = notfallkontaktAddresse;
    }

    public String getNotfallkontaktPostleitzahl() {
        return notfallkontaktPostleitzahl;
    }

    public void setNotfallkontaktPostleitzahl(String notfallkontaktPostleitzahl) {
        this.notfallkontaktPostleitzahl = notfallkontaktPostleitzahl;
    }

    public String getNotfallkontaktEmail() {
        return notfallkontaktEmail;
    }

    public void setNotfallkontaktEmail(String notfallkontaktEmail) {
        this.notfallkontaktEmail = notfallkontaktEmail;
    }
}
