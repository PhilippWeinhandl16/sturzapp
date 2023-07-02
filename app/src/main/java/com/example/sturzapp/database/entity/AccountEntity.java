package com.example.sturzapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/**
 * Die Klasse {@code AccountEntity} repräsentiert ein Account-Objekt in der
 * {@link com.example.sturzapp.database.SturzappDatabase}
 * Sie wird als Entity in der Room-Datenbank verwendet und enthält die Attribute eines Accounts
 */
@Entity(tableName = "accounts")
public class AccountEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String emailRP;
    private String passwordRP;
    private String firstNameRP;
    private String lastNameRP;
    private String emailNFK;
    private String nameNFK;

    /**
     * Konstruktor für einen leeren Account.
     */
    public AccountEntity() {
    }

    /**
     * Konstruktor für einen Account mit allen Attributen.
     * @param emailRP     die E-Mail-Adresse des Accounts (RP steht für Risikopatient)
     * @param passwordRP  das Passwort des Accounts (RP steht für Risikopatient)
     * @param firstNameRP der Vorname des Accounts (RP steht für Risikopatient)
     * @param lastNameRP  der Nachname des Accounts (RP steht für Risikopatient)
     * @param emailNFK    die E-Mail-Adresse des Accounts (NFK steht für Notfallkontakt)
     * @param nameNFK     der Name des Accounts (NFK steht für Notfallkontakt)
     */
    public AccountEntity(String emailRP, String passwordRP, String firstNameRP, String lastNameRP, String emailNFK, String nameNFK) {
        this.emailRP = emailRP;
        this.passwordRP = passwordRP;
        this.firstNameRP = firstNameRP;
        this.lastNameRP = lastNameRP;
        this.emailNFK = emailNFK;
        this.nameNFK = nameNFK;
    }

    /**
     * Gibt die ID des Accounts zurück
     * @return die ID des Accounts
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt die E-Mail-Adresse des Risikopatienten zurück
     * @return die E-Mail-Adresse des Accounts
     */
    public String getEmailRP() {
        return emailRP;
    }

    /**
     * Gibt das Passwort des Risikopatienten zurück
     * @return das Passwort des Accounts
     */
    public String getPasswordRP() {
        return passwordRP;
    }

    /**
     * Gibt den Vornamen des Risikopatienten zurück
     * @return der Vorname des Accounts
     */
    public String getFirstNameRP() {
        return firstNameRP;
    }

    /**
     * Gibt den Nachnamen des Risikopatienten zurück
     * @return der Nachname des Accounts
     */
    public String getLastNameRP() {
        return lastNameRP;
    }

    /**
     * Gibt die E-Mail-Adresse des Notfallkontakts zurück
     * @return die E-Mail-Adresse des Notfallkontakts
     */
    public String getEmailNFK() {
        return emailNFK;
    }

    /**
     * Gibt den Namen des Notfallkontakts zurück
     * @return der Name des Notfallkontakts
     */
    public String getNameNFK() {
        return nameNFK;
    }

    /**
     * Setzt die ID des Accounts
     * @param id die ID des Accounts
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setzt die E-Mail-Adresse des Accounts
     * @param emailRP die E-Mail-Adresse des Accounts
     */
    public void setEmailRP(String emailRP) {
        this.emailRP = emailRP;
    }

    /**
     * Setzt das Passwort des Accounts
     * @param passwordRP das Passwort des Accounts
     */
    public void setPasswordRP(String passwordRP) {
        this.passwordRP = passwordRP;
    }

    /**
     * Setzt den Vornamen des Accounts
     * @param firstNameRP der Vorname des Accounts
     */
    public void setFirstNameRP(String firstNameRP) {
        this.firstNameRP = firstNameRP;
    }

    /**
     * Setzt den Nachnamen des Accounts
     * @param lastNameRP der Nachname des Accounts
     */
    public void setLastNameRP(String lastNameRP) {
        this.lastNameRP = lastNameRP;
    }

    /**
     * Setzt die E-Mail-Adresse des Notfallkontakts
     * @param emailNFK die E-Mail-Adresse des Notfallkontakts
     */
    public void setEmailNFK(String emailNFK) {
        this.emailNFK = emailNFK;
    }

    /**
     * Setzt den Namen des Notfallkontakts
     * @param nameNFK der Name des Notfallkontakts
     */
    public void setNameNFK(String nameNFK) {
        this.nameNFK = nameNFK;
    }

    /**
     * Gibt eine textuelle Repräsentation des Account-Objekts zurück
     * Die Ausgabe besteht aus dem Vor- und Nachnamen des Accounts
     * gefolgt von einem Bindestrich und der E-Mail-Adresse des Accounts.
     * @return eine textuelle Repräsentation eines Account-Objekts
     */
    @Override
    public String toString() {
        return firstNameRP + lastNameRP + " - " + emailRP;
    }
}
