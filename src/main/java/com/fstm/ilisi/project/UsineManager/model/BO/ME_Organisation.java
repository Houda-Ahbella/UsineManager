package com.fstm.ilisi.project.UsineManager.model.BO;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "me_organisation")
public class ME_Organisation {
    @EmbeddedId
    KeyEtapeMarque id ;
    @ManyToOne
    @MapsId("MarqueId")
    @JoinColumn(name = "mrq_id")
    Marque marque;
    @ManyToOne
    @MapsId("StepId")
    @JoinColumn(name = "step_id")
    Step step;
     @Column(unique = true)
    int ordre;

    public ME_Organisation(KeyEtapeMarque id, Step etape, Marque marque, int ordre) {
        this.id = id;
        this.step = etape;
        this.marque = marque;
        this.ordre = ordre;
    }

    public ME_Organisation() {
    }

    public KeyEtapeMarque getId() {
        return id;
    }

    public void setId(KeyEtapeMarque id) {
        this.id = id;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step etape) {
        this.step = etape;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    @Override
    public String toString() {
        return "ME_Organisation{" +
                "id=" + id +
                ", step=" + step +
                ", marque=" + marque +
                ", ordre=" + ordre +
                '}';
    }
    public static Comparator<ME_Organisation> ComparatorOrder = new Comparator<ME_Organisation>()
    {
        @Override
        public int compare(ME_Organisation v1, ME_Organisation v2)
        {
            return (v1.ordre- v2.ordre);
        }
    };
}
