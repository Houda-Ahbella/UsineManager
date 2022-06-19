package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Fin_Etape;
import com.fstm.ilisi.project.UsineManager.model.BO.KeyvehiculeEtape;
import com.fstm.ilisi.project.UsineManager.model.BO.Step;
import com.fstm.ilisi.project.UsineManager.model.BO.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public interface Fin_EtapeRepo extends JpaRepository<Fin_Etape, KeyvehiculeEtape> {
    int countByStepAndEtatAndDatefin( Step step , String et, LocalDate date);
    int countByStepAndEtat(Step step , String et);
    @Query("SELECT COUNT (distinct e.vehicule) from Fin_Etape e where e.etat=:et " )
  //  AND vehicule_id NOT IN( SELECT DISTINCT vehicule_id FROM fin_etape WHERE etat="Fini" AND step_id = 10)
    int countDistinctByEtat(@Param("et") String et);
    @Query("SELECT COUNT (distinct e.vehicule) from Fin_Etape e where e.etat=:et and  e.datefin=:d")
    int countFin_Etape(@Param("et") String et ,@Param("d") LocalDate d );

    List<Fin_Etape> findAllByStepAndEtat(Step ste, String etat );
    List<Fin_Etape> findByVehiculeAndEtatIn(Vehicule v, Collection<String> etats);
    List<Fin_Etape> findAllByEtat(String s);
    List<Fin_Etape> findByOrdreLessThanAndVehicule(int ordre, Vehicule ve);

}

