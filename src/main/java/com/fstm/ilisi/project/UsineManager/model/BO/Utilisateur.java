package com.fstm.ilisi.project.UsineManager.model.BO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 25, nullable = false)
    String nom ;
    @Column(length = 25)
    String prenom ;
    @OneToOne(mappedBy = "utilisateur")
    @JoinColumn(name = "email")
    Compte compte;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Utilisateur() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "utilisateur")
    private Set<Roleutili> roles = new HashSet<>();

    public Set<Roleutili> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roleutili> roles) {
        this.roles = roles;
    }
    public int getCount()
    {
        return this.roles.size();
    }
   public String getrole()
   {
       String s = "";
       for (Roleutili r: this.roles )
           s = r.getRole().getNom() + " - "+s  ;
       return s;
   }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", compte=" + compte +
                ", roles=" + roles +
                '}';
    }
}
