package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Compte;
import com.fstm.ilisi.project.UsineManager.model.BO.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepo extends JpaRepository<Compte,Integer > {
    Compte findByUtilisateur(Utilisateur u);
    boolean existsByEmail(String e);
    Compte findByEmail(String e);

}
