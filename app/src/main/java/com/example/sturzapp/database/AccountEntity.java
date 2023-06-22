package com.example.sturzapp.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

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


        // Getter-Methoden
        public int getId() {
            return id;
        }

        public String getEmailRP() {
            return emailRP;
        }

        public String getPasswordRP() {
            return passwordRP;
        }

        public String getFirstNameRP() {
            return firstNameRP;
        }

        public String getLastNameRP() {
            return lastNameRP;
        }

        public String getEmailNFK() {
            return emailNFK;
        }

        public String getNameNFK() {
            return nameNFK;
        }


    //Setter-Methoden
    public void setId(int id) {
        this.id = id;
    }

    public void setEmailRP(String emailRP) {
        this.emailRP = emailRP;
    }

    public void setPasswordRP(String passwordRP) {
        this.passwordRP = passwordRP;
    }

    public void setFirstNameRP(String firstNameRP) {
        this.firstNameRP = firstNameRP;
    }

    public void setLastNameRP(String lastNameRP) {
        this.lastNameRP = lastNameRP;
    }

    public void setEmailNFK(String emailNFK) {
        this.emailNFK = emailNFK;
    }

    public void setNameNFK(String nameNFK) {
        this.nameNFK = nameNFK;
    }


    @Override
    public String toString() {
        return firstNameRP + lastNameRP + " - " + emailRP;
    }
}
