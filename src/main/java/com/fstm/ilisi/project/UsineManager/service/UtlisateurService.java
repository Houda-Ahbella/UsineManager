package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.Compte;
import com.fstm.ilisi.project.UsineManager.model.BO.Utilisateur;
import com.fstm.ilisi.project.UsineManager.model.DBA.UtilisateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UtlisateurService {
    private final UtilisateurRepo utilisateurRepo;
    @Autowired
    public UtlisateurService(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }
    public List<Utilisateur> findAll()
    {
        return utilisateurRepo.findAll();
    }
    public Utilisateur findbycompte(Compte c)
    {
        if(utilisateurRepo.existsByCompte(c))
        return utilisateurRepo.findByCompte(c);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(-1);
        return utilisateur;
    }
    public Utilisateur findbyid(int id)
    {
        return utilisateurRepo.findById(id).get();
    }
    public List<Utilisateur> findbynom(String nom )
    {
          if(utilisateurRepo.existsByNom(nom))
          {
              return (List<Utilisateur>) utilisateurRepo.findByNom(nom);
          }
          List<Utilisateur> list= new ArrayList<>();

          Utilisateur u = new Utilisateur();
          u.setId(-1);
          list.add(u);
          return list;
    }
    public void delete(Utilisateur id)
    {
        utilisateurRepo.delete(id);
    }
    public Utilisateur add(Utilisateur U)
    {
        if(utilisateurRepo.existsByNomAndPrenom(U.getNom(),U.getPrenom()))
        {

            U.setId(-1);
            return U;
        }
        return utilisateurRepo.save(U);
    }
    public Utilisateur getbyid( int id)
    {
        return utilisateurRepo.findById(id).get();
    }
    public Boolean exitsbynomandorenom(String nom , String prenom)
    {
        return utilisateurRepo.existsByNomAndPrenom(nom,prenom);
    }
    public Utilisateur save(Utilisateur u)
    {
        return utilisateurRepo.save(u);
    }

}
