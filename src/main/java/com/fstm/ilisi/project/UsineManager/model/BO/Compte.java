package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Compte")
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCompte;
    @Column(length = 25, nullable = false)
    String email;
    @Column(length = 25, nullable = false)
    String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    Utilisateur utilisateur;
    @JsonIgnore
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Compte() {
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

    @Override
    public String toString() {
        return "Compte{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int id) {
        this.idCompte = id;
    }
}
