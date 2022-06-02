package com.fstm.ilisi.project.UsineManager.model.BO;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class KeyProblemveh  implements Serializable {
    private static final long serialVersionUID = -3176376525721551423L;

        @Column(name = "probleme_id")
        int ProblemeId;
        @Column(name = "vehicule_id")
        String VehiculeId;

         public KeyProblemveh() {
          }

    public int getProblemeId() {
        return ProblemeId;
    }

    public void setProblemeId(int problemeId) {
        ProblemeId = problemeId;
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
        KeyProblemveh that = (KeyProblemveh) o;
        return ProblemeId == that.ProblemeId && Objects.equals(VehiculeId, that.VehiculeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ProblemeId, VehiculeId);
    }

    @Override
    public String toString() {
        return "KeyProblemveh{" +
                "ProblemeId=" + ProblemeId +
                ", VehiculeId='" + VehiculeId + '\'' +
                '}';
    }
}
