package com.fstm.ilisi.project.UsineManager.model.BO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KeyvehiculeEtape implements Serializable {
    @Column(name = "step_id")
    int StepId;
    @Column(name = "vehicule_id")
    String VehiculeId;

    public KeyvehiculeEtape() {
    }

    public int getStepId() {
        return StepId;
    }

    public void setStepId(int stepId) {
        StepId = stepId;
    }

    public String getVehiculeId() {
        return VehiculeId;
    }

    public void setVehiculeId(String vehiculeId) {
        VehiculeId = vehiculeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyvehiculeEtape that = (KeyvehiculeEtape) o;
        return StepId == that.StepId && Objects.equals(VehiculeId, that.VehiculeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StepId, VehiculeId);
    }

    @Override
    public String toString() {
        return "KeyvehiculeEtape{" +
                "StepId=" + StepId +
                ", VehiculeId='" + VehiculeId + '\'' +
                '}';
    }
}
