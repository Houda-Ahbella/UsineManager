package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "vehiculeprobleme")
public class VehiculeProbleme {

    @EmbeddedId
    KeyProblemveh key;
    @ManyToOne
    @MapsId("VehiculeId")
    @JoinColumn(name = "vehicule_id")
    Vehicule vehicule;
    @ManyToOne
    @MapsId("ProblemeId")
    @JoinColumn(name = "probleme_id")
    Probleme_qualite prb;

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    private String observation;

    public VehiculeProbleme() {
    }

    public KeyProblemveh getKey() {
        return key;
    }

    public void setKey(KeyProblemveh key) {
        this.key = key;
    }
    @JsonIgnore
    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
    @JsonIgnore
    public Probleme_qualite getPrb() {
        return prb;
    }

    public void setPrb(Probleme_qualite prb) {
        this.prb = prb;
    }

    @Override
    public String toString() {
        return "VehiculeProbleme{" +
                "key=" + key +
                ", vehicule=" + vehicule +
                ", prb=" + prb +
                '}';
    }
    public String getNom()
    {
        return this.prb.getDesignation();
    }

}
