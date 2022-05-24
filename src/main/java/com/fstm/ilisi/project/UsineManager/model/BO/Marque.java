package com.fstm.ilisi.project.UsineManager.model.BO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "marque")
public class Marque implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num_marque;
    public int getNum_marque() {
        return num_marque;
    }

    public void setNum_marque(int num_marque) {
        this.num_marque = num_marque;
    }

    @Column(name = "designation" , length = 40 , nullable = false , unique = true)
    private String designation;
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    private String URL;
    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "marque")
    private Set<Modele> modeles = new HashSet<>();
    public void setModeles(Set<Modele> m) {
        this.modeles = m;
    }
    public Set<Modele> getModeles() {
        return modeles;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "marque")
    private Set<ME_Organisation> Steps = new HashSet<>();
    @JsonIgnore
    public Set<ME_Organisation> getSteps() {
        return Steps;
    }
    public void setSteps(Set<ME_Organisation> ETAPES) {
        this.Steps = ETAPES;
    }

    public Marque() {}

    public Marque(String desig) {designation=desig;}

    @Override
    public String toString() { return designation ;}








}
