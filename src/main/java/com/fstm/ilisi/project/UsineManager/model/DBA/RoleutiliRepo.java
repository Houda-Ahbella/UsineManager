package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Keyroleutili;
import com.fstm.ilisi.project.UsineManager.model.BO.Roleutili;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleutiliRepo extends JpaRepository<Roleutili, Keyroleutili> {
}
