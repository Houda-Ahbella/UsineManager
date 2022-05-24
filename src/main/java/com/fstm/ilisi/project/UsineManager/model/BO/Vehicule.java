package com.fstm.ilisi.project.UsineManager.model.BO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "Vehicule")
public class Vehicule implements Serializable {
    @Id
    private String Num_Chassis;
    private String Num_Engine;
    private String Couleur;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot")
    private Lot lot;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modele")
    private Modele modele;
    private int Ordre;

    public Modele getModele() {
        return modele;
    }

    public void setModele(Modele marque) {
        this.modele = marque;
    }


    public Lot getLot() {
        return lot;
    }

    public String getNum_Chassis() {
        return Num_Chassis;
    }

    public void setNum_Chassis(String id) {
        this.Num_Chassis = id;
    }

    public String getNum_Engine() {
        return Num_Engine;
    }

    public void setNum_Engine(String tel) {
        this.Num_Engine = tel;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String nom) {
        this.Couleur = nom;
    }


    public Vehicule(String id, String engine, String col, Modele m , Lot lot) {
        this.Num_Chassis = id;
        this.Num_Engine = engine;
        this.Couleur = col;
        this.modele =m;
        this.lot=lot;
    }

    public Vehicule() {
    }

    @Override
    public String toString() {
        return
                "numchassis=" + Num_Chassis +
                        ", numEngine='" + Num_Engine + '\'' +
                        ", couleur='" + Couleur + '\'' +
                        ", modele ='" + modele + '\'' +
                     //   ", marque ='" + modele.getMarque() + '\'' +
                        ", lot=" + lot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicule contact = (Vehicule) o;
        return Objects.equals(Num_Chassis, contact.Num_Chassis);
    }

    public void setLot(Lot lots) {
        this.lot = lots;
    }

    public int getOrdre() {
        return Ordre;
    }

    public void setOrdre(int ordre) {  Ordre = ordre; }
   public static Comparator<Vehicule> ComparatorOrder = new Comparator<Vehicule>()
   {
       @Override
       public int compare(Vehicule v1, Vehicule v2)
       {
           return (v1.Ordre- v2.Ordre);
       }
   };
}
