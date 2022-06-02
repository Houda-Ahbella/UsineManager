package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Fin_Etape;
import com.fstm.ilisi.project.UsineManager.model.BO.KeyvehiculeEtape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Fin_EtapeRepo extends JpaRepository<Fin_Etape, KeyvehiculeEtape> {
}
