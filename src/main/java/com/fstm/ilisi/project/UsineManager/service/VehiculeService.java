package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.Modele;
import com.fstm.ilisi.project.UsineManager.model.BO.Vehicule;
import com.fstm.ilisi.project.UsineManager.model.DBA.VehiculeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class VehiculeService {
    private final VehiculeRepo vehiculerepo;
    @Autowired
    public VehiculeService(VehiculeRepo vehiculerepo) { this.vehiculerepo = vehiculerepo;}




    public Vehicule addVehicule(Vehicule vehicule) {
        return vehiculerepo.save(vehicule);
    }

    public List<Vehicule> findAllVehicules() {
        return vehiculerepo.findAll();
    }

    public Vehicule updateVehicule(Vehicule vehicule) {
        return vehiculerepo.save(vehicule);
    }

    public Vehicule findVehiculeById(String id) {
        return vehiculerepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteVehicule(String id){

        vehiculerepo.deleteById(id);
    }
}
