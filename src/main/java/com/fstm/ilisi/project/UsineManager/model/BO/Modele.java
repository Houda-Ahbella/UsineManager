package com.fstm.ilisi.project.UsineManager.model.BO;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "modele")
// @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Modele implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num_modele;
    public int getNum_modele() {
        return num_modele;
    }

    public void setNum_modele(int num_modele) {
        this.num_modele = num_modele;
    }

    @Column(name = "designation" , length = 40 , nullable = false , unique = true)
    private String designation;
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marque" , nullable = false)
    private Marque marque;
    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Modele() {
    }
    @Override
    public String toString() { return designation ; }





}
