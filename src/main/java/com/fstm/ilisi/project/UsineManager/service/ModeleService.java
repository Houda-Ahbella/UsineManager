package com.fstm.ilisi.project.UsineManager.service;
import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.Modele;
import com.fstm.ilisi.project.UsineManager.model.DBA.ModeleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ModeleService {
    private final ModeleRepo modelerepo;

    @Autowired
    public ModeleService(ModeleRepo modelerepo) {
        this.modelerepo = modelerepo;
    }

    public Modele addModele(Modele modele)
    {
       if(modelerepo.existsByDesignation(modele.getDesignation())==true)
           return null;
        return modelerepo.save(modele);
    }

    public List<Modele> findAllModeles() {
        return modelerepo.findAll();
    }

    public Modele updateModele(Modele modele) {
        return modelerepo.save(modele);
    }

    public Modele findModeleById(int id) {
        return modelerepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
    public Modele findbydes(String des)
    {
        return modelerepo.findByDesignation(des);
    }

    public void deleteModele(int id){
        modelerepo.deleteById(id);
    }
}
