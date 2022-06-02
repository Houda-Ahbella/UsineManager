package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Lot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LotRepo extends JpaRepository<Lot,Integer> {

}
