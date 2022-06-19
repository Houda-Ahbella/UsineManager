package com.fstm.ilisi.project.UsineManager.model.BO;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Keyroleutili implements Serializable {
    @Column(name = "role_id")
    int RoleId;
    @Column(name = "utilisateur_id")
    int UtlisateurId ;

    public Keyroleutili() {
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public int getUtlisateurId() {
        return UtlisateurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyroleutili that = (Keyroleutili) o;
        return RoleId == that.RoleId && UtlisateurId == that.UtlisateurId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(RoleId, UtlisateurId);
    }

    public void setUtlisateurId(int utlisateurId) {
        UtlisateurId = utlisateurId;
    }

    @Override
    public String toString() {
        return "Keyroleutili{" +
                "RoleId=" + RoleId +
                ", UtlisateurId=" + UtlisateurId +
                '}';
    }
}
