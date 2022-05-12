package com.fstm.ilisi.project.UsineManager.model.BO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "marque")
public class Marque implements Serializable{
    @Id
    private String designation;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }


    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "marque")
    private Set<Modele> modeles = new HashSet<>();
    private String URL;

    public Marque() {

    }




    @Override
    public String toString() {
        return designation   ;

    }

    public String getDesignation() {
        return designation;
    }



    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setModeles(Set<Modele> m) {
        this.modeles = m;
    }

    public Set<Modele> getModeles() {
        return modeles;
    }

    public Marque(String desig) {

        designation=desig;
    }
}
