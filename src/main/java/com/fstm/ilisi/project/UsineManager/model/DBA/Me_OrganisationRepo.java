package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.KeyEtapeMarque;
import com.fstm.ilisi.project.UsineManager.model.BO.ME_Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Me_OrganisationRepo extends JpaRepository<ME_Organisation, KeyEtapeMarque> {
  boolean existsByOrdre(int ordre);
}
