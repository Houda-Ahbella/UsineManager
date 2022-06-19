package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Role")
public class Role implements Serializable {
    @Id
    int id;
    @Column(length = 50, nullable = false)
    String nom;
    @Column(length = 100)
    String Explication;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getExplication() {
        return Explication;
    }

    public void setExplication(String explication) {
        Explication = explication;
    }
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    private Set<Roleutili> roles = new HashSet<>();
   @JsonIgnore
    public Set<Roleutili> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roleutili> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", Explication='" + Explication + '\'' +
                '}';
    }
}
