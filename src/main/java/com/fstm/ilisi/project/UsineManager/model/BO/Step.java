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
@Table(name = "Etape")
public class Step implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 25, nullable = false,unique = true)
    private String des;

    public Step(int id, String des) {
        this.id = id;
        this.des = des;
    }

    public Step() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "step")
    private Set<ME_Organisation> Steps = new HashSet<>();
    @JsonIgnore
    public Set<ME_Organisation> getSteps() {
        return Steps;
    }
    public void setSteps(Set<ME_Organisation> ETAPES) {
        this.Steps = ETAPES;
    }


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "step")
    private Set<Fin_Etape> fin_etapes = new HashSet<>();

    public Set<Fin_Etape> getFin_etapes() {
        return fin_etapes;
    }

    public void setFin_etapes(Set<Fin_Etape> fin_etapes) {
        this.fin_etapes = fin_etapes;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", des='" + des + '\'' +
                '}';
    }

}
