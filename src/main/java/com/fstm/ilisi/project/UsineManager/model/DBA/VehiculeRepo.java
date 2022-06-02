package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Lot;
import com.fstm.ilisi.project.UsineManager.model.BO.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepo extends JpaRepository<Vehicule,String> {
    //boolean existsByNum_Engine(String en);
   // boolean existsByNum_EngineIsLike(String Num_Engine);
    boolean existsByOrdreAndLot(int ordre, Lot lot);
    boolean existsByNumengine(String num);
    List<Vehicule> findAllByLot(Lot lot);
    List<Vehicule> findAllByLotOrderByOrdre(Lot lot);
}
