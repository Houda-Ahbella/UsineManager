package com.fstm.ilisi.project.UsineManager.model.BO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Roleutili")
public class Roleutili {
    @EmbeddedId
    Keyroleutili key;
    @ManyToOne
    @MapsId("RoleId")
    @JoinColumn(name = "role_id")
    Role role;
    @ManyToOne
    @MapsId("UtlisateurId")
    @JoinColumn(name = "utilisateur_id")
    Utilisateur utilisateur;

    public Keyroleutili getKey() {
        return key;
    }

    public void setKey(Keyroleutili key) {
        this.key = key;
    }
   @JsonIgnore
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
  @JsonIgnore
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Roleutili{" +
                "key=" + key +
                '}';
    }
}
