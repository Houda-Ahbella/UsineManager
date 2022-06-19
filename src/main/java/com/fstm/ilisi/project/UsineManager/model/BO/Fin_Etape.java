package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "fin_etape")
public class Fin_Etape implements Serializable
{
    @EmbeddedId
    KeyvehiculeEtape key;
    @ManyToOne
    @MapsId("StepId")
    @JoinColumn(name = "step_id")
    Step step;
    @ManyToOne
    @MapsId("VehiculeId")
    @JoinColumn(name = "vehicule_id")
    Vehicule vehicule;
    LocalDate datefin;
    @Column(length = 10 )
    String etat;
    int ordre ;
    public Fin_Etape() {
    }

    public KeyvehiculeEtape getKey() {
        return key;
    }

    public void setKey(KeyvehiculeEtape key) {
        this.key = key;
    }
    @JsonIgnore
    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
    @JsonIgnore
    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public LocalDate getDatefin() {
        return datefin;
    }

    public void setDatefin(LocalDate date_fin) {
        this.datefin = date_fin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNomStep()
    {
        if(this.step==null) return "";
        return this.step.getDes();
    }

    @Override
    public String toString() {
        return "Fin_Etape{" +
                "key=" + key +
                ", step=" + step +
                ", vehicule=" + vehicule +
                ", date_fin=" + datefin +
                ", Etat='" + etat + '\'' +
                '}';
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
    public static Comparator<Fin_Etape> ComparatorOrder = new Comparator<Fin_Etape>()
    {
        @Override
        public int compare(Fin_Etape v1, Fin_Etape v2)
        {
            return (v1.ordre - v2.ordre);
        }
    };
}
