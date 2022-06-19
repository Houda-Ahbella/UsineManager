package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Vehicule")
public class Vehicule implements Serializable {
    @Id
    @Column(length = 20 ,nullable = false)
    private String Num_Chassis;
    @Column(length = 15 , unique = true , nullable = false)
    private String numengine;
    @Column(length = 10 )
    private String Couleur;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot",nullable = false)
    private Lot lot;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modele",nullable = false)
    private Modele modele;
    private int ordre;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vehicule")
    private Set<Fin_Etape> roles = new HashSet<>();

    public List<Fin_Etape> getSteps()
    {
        List<Fin_Etape> stt = new ArrayList<>(roles);
        Collections.sort(stt, Fin_Etape.ComparatorOrder);
        return stt;
    }

    public void setSteps(Set<Fin_Etape> steps) { roles = steps;}
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

    public String getNumengine() {
        return numengine;
    }

    public void setNumengine(String tel) {
        this.numengine = tel;
    }

    public String getCouleur() {
        return Couleur;
    }

    public void setCouleur(String nom) {
        this.Couleur = nom;
    }


    public Vehicule(String id, String engine, String col, Modele m , Lot lot) {
        this.Num_Chassis = id;
        this.numengine = engine;
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
                        ", numEngine='" + numengine + '\'' +
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
        return ordre;
    }

    public void setOrdre(int ordre) {  this.ordre = ordre; }
   public static Comparator<Vehicule> ComparatorOrder = new Comparator<Vehicule>()
   {
       @Override
       public int compare(Vehicule v1, Vehicule v2)
       {
           return (v1.ordre - v2.ordre);
       }
   };

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "vehicule")
    private Set<VehiculeProbleme> problemes = new HashSet<>();
    public Set<VehiculeProbleme> getProblemes() {
        return problemes;
    }

    public void setProblemes(Set<VehiculeProbleme> problemes) {
        this.problemes = problemes;
    }


}
