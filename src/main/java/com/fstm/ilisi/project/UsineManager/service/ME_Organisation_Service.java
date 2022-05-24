package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.KeyEtapeMarque;
import com.fstm.ilisi.project.UsineManager.model.BO.ME_Organisation;
import com.fstm.ilisi.project.UsineManager.model.DBA.Me_OrganisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ME_Organisation_Service {
    private final Me_OrganisationRepo organiseRepo;
    @Autowired
    public ME_Organisation_Service(Me_OrganisationRepo organiseRepo) {
        this.organiseRepo = organiseRepo;
    }




    public ME_Organisation addEtapeMarque(ME_Organisation orga) {

        return organiseRepo.save(orga);
    }

    public List<ME_Organisation> findAllEtapesMarque() {
        return organiseRepo.findAll();
    }

    public ME_Organisation updateEtapeMarque(ME_Organisation step) {
        return organiseRepo.save(step);
    }

    public ME_Organisation findeEtapeMarqueById(KeyEtapeMarque id) {
        return organiseRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEtapeMarque(KeyEtapeMarque id){
        organiseRepo.deleteById(id);
    }
    public boolean exitByOrdre(int ordre)
    {
        return organiseRepo.existsByOrdre(ordre);
    }

}
