package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Compte;
import com.fstm.ilisi.project.UsineManager.model.BO.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UtilisateurRepo extends JpaRepository<Utilisateur,Integer>
{
    Utilisateur findByCompte(Compte c);
    Boolean existsByCompte(Compte c);
    Boolean existsByNomAndPrenom( String n , String p);
    Boolean existsByNom(String n);
    List<Utilisateur> findByNom(String n);

}
