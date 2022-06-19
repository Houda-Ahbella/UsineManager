package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.Compte;
import com.fstm.ilisi.project.UsineManager.model.BO.Utilisateur;
import com.fstm.ilisi.project.UsineManager.model.DBA.CompteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CompteService{
    private final CompteRepo compteRepo;

    @Autowired
    public CompteService(CompteRepo compteRepo) {
        this.compteRepo = compteRepo;
    }
    public Utilisateur FindByid(Compte c)
    {
        // compte n'exite pas
        if(!compteRepo.existsByEmail(c.getEmail()))
        {
            Utilisateur u = new Utilisateur();
            u.setNom("ce compte n'exite pas");
            u.setId(-1);
            return u;
        }
        // existe
        else
        {
            Compte co = compteRepo.findByEmail(c.getEmail());
            // mot de passe incorrecte
            if(!co.getPassword().equals(c.getPassword()))
            {
                Utilisateur u = new Utilisateur();
                u.setId(-1);
                u.setNom("mot de passe incorrect");
                return u;
            }
            return co.getUtilisateur();
        }

    }
    public void delete(Compte c)
    {
        compteRepo.delete(c);
    }
    public Compte findbyUser(Utilisateur u)
    {
        return compteRepo.findByUtilisateur(u);
    }
    public Compte add(Compte c)
    {
        if(compteRepo.existsByEmail(c.getEmail()))
        {
            c.setEmail("");
            return c;
        }
        return compteRepo.save(c);
    }
    public Boolean existbyid(String em)
    {
        return compteRepo.existsByEmail(em);
    }
    public Compte save(Compte c)
    {
        return  compteRepo.save(c);
    }

}
