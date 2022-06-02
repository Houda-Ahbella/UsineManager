package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Lot")
public class Lot implements Serializable {
    @Id
    @Column(nullable = false)
    private int Num_lot;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "lot")
    private Set<Vehicule> vehicules = new HashSet<>();
    private int Nombre_vehicules;
    @Column(length = 50)
    private String Num_bach;
    @Column(length = 20 )
    private String Connaissement;
    @Column(length = 12)
    private String Date_Entree;
    public Lot() {
    }

    public int getNombre_vehicules() {
        return Nombre_vehicules;
    }

    public void setNombre_vehicules() {
        Nombre_vehicules = this.getVehicules().size();
    }

    public void setNombre_vehicules(int a) {
        Nombre_vehicules = a;
    }

    public int getNum_lot() {
        return this.Num_lot;
    }

    public void setNum_lot(int type) {
        this.Num_lot = type;
    }




    public Modele getModeleLot()
    { if(this.vehicules.size()==0)
    {
        System.out.println("Le lot est vide");
        return null;
    }
        return this.vehicules.iterator().next().getModele();
    }
    public void setModeleLot(Modele modele)
    { if(this.vehicules.size()==0)
    {
        System.out.println("Le lot est vide");

    }
    else
    {
        for(Vehicule v: this.getVehicules())
            v.setModele(modele);
    }
    }
    @JsonIgnore
    public Set<Vehicule> getVehicules() {
        return vehicules;
    }

    public void setVehicules(Set<Vehicule> vehicules) {
        this.vehicules = vehicules;
    }

    public String getNum_bach() {
        return Num_bach;
    }

    public void setNum_bach(String num_bach) {
        Num_bach = num_bach;
    }

    public String getConnaissement() {
        return Connaissement;
    }

    @Override
    public String toString() {
        return "Lot{" +
                "Num_lot=" + Num_lot +
                ", Nombre_vehicules=" + Nombre_vehicules +
                ", Num_bach='" + Num_bach + '\'' +
                ", Connaissement='" + Connaissement + '\'' +
                ", Date_Entree='" + Date_Entree + '\'' +
                '}';
    }

    public void setConnaissement(String connaissement) {
        Connaissement = connaissement;
    }

    public String getDate_Entree() {
        return Date_Entree;
    }

    public void setDate_Entree(String date_Entree) {
        Date_Entree = date_Entree;
    }

    public static Comparator<Lot> ComparatorOrder = new Comparator<Lot>()
    {
        @Override
        public int compare(Lot v1, Lot v2)
        {
            return (v1.Num_lot- v2.Num_lot);
        }
    };
}
