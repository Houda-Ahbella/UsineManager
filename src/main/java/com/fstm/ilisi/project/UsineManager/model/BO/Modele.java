package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "modele")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Modele implements Serializable{
    public Modele() {
    }

    public Modele(String designation, Marque m) {

        this.designation = designation;
      //  this.vehicules = vehicules;
        this.marque=m;
    }



    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    // public Set<Vehicule> getVehicules() {return vehicules;}

  //  public void setVehicules(Set<Vehicule> vehicules) {this.vehicules = vehicules;}
    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }
    @Id
    private String designation;
    /*@OneToMany(fetch = FetchType.LAZY,mappedBy = "modele")
    private Set<Vehicule> vehicules = new HashSet<>(); */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marque")
    private Marque marque;
    @Override
    public String toString() { return designation ; }
}
