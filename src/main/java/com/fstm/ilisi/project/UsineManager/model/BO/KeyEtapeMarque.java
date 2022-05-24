package com.fstm.ilisi.project.UsineManager.model.BO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KeyEtapeMarque implements Serializable {
    private static final long serialVersionUID = -3176376525721551423L;


    @Column(name = "mrq_id")
    int MarqueId;

    @Column(name = "step_id")
    int StepId;

    public KeyEtapeMarque(int etId, int marqueId) {
        StepId = etId;
        MarqueId = marqueId;
    }

    public KeyEtapeMarque() {
    }

    public int getStepId() {
        return StepId;
    }

    public void setStepId(int stepId) {
        StepId = stepId;
    }

    public int getMarqueId() {
        return MarqueId;
    }

    public void setMarqueId(int marqueId) {
        MarqueId = marqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyEtapeMarque that = (KeyEtapeMarque) o;
        return StepId == that.StepId && MarqueId == that.MarqueId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(StepId, MarqueId);
    }

    @Override
    public String toString() {
        return "KeyEtapeMarque{" +
                "MarqueId='" + MarqueId + '\'' +
                ", StepId=" + StepId +
                '}';
    }
}
