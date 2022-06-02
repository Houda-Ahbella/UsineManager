package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.*;
import com.fstm.ilisi.project.UsineManager.model.DBA.VehiculeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VehiculeService {
    private final VehiculeRepo vehiculerepo;
    @Autowired
    public VehiculeService(VehiculeRepo vehiculerepo) { this.vehiculerepo = vehiculerepo;}




    public Vehicule addVehicule(Vehicule vehicule) {
        if(vehiculerepo.existsById(vehicule.getNum_Chassis()))
        {
            Vehicule ve = new Vehicule();
            ve.setOrdre(-1);
            return ve;
        }
        if(vehiculerepo.existsByOrdreAndLot(vehicule.getOrdre(), vehicule.getLot()))
        {
            Vehicule ve = new Vehicule();
            ve.setOrdre(-2);
            return ve;
        }



        if(vehiculerepo.existsByNumengine(vehicule.getNumengine()))
        {
            Vehicule ve = new Vehicule();
            ve.setOrdre(-3);
            return ve;
        }

        return vehiculerepo.save(vehicule);
    }

    public List<Vehicule> findAllVehicules() {
        return vehiculerepo.findAll();
    }

    public Vehicule updateVehicule(Vehicule vehicule) {
        if(vehiculerepo.existsByNumengine(vehicule.getNumengine()))
        {
            Vehicule ve = new Vehicule();
            ve.setOrdre(-3);
            return ve;
        }
        return vehiculerepo.save(vehicule);
    }

    public Vehicule findVehiculeById(String id) {
        return vehiculerepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteVehicule(String id){

        vehiculerepo.deleteById(id);
    }
    public List<Vehicule> findAllBloque(Lot id)
    {

       // List<Vehicule> ves = vehiculerepo.findAllByLot(id);
        List<Vehicule> ves = vehiculerepo.findAllByLotOrderByOrdre(id);
        List<Vehicule> all = new ArrayList<>();
        for(Vehicule v : ves)
            if(bloqueVehicule(v)) all.add(v);
        return all;

    }
    public boolean bloqueVehicule(Vehicule V)
    {
        for(Fin_Etape v : V.getSteps())
            if(v.getEtat().equals("Bloqué"))
                return true;
        return false;
    }
}
