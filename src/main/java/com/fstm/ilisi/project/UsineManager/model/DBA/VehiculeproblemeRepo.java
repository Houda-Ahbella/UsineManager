package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.KeyProblemveh;
import com.fstm.ilisi.project.UsineManager.model.BO.Probleme_qualite;
import com.fstm.ilisi.project.UsineManager.model.BO.VehiculeProbleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehiculeproblemeRepo extends JpaRepository<VehiculeProbleme, KeyProblemveh> {
    int countAllByPrb(Probleme_qualite prb);

}
