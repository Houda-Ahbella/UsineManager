package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "probleme_qualite")
public class Probleme_qualite implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(length = 50,unique = true)
    private String designation;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "prb")
    private Set<VehiculeProbleme> problemes = new HashSet<>();
    @JsonIgnore
    public Set<VehiculeProbleme> getProblemes() {
        return problemes;
    }

    public void setProblemes(Set<VehiculeProbleme> problemes) {
        this.problemes = problemes;
    }


    public Probleme_qualite() {
    }

    @Override
    public String toString() {
        return "Probleme_qualite{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                '}';
    }
    public int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
