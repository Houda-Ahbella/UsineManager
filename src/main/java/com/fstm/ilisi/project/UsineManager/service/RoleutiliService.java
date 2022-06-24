package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.Keyroleutili;
import com.fstm.ilisi.project.UsineManager.model.BO.Roleutili;
import com.fstm.ilisi.project.UsineManager.model.DBA.RoleutiliRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleutiliService {
    private final RoleutiliRepo roleutiliRepo;
   @Autowired
    public RoleutiliService(RoleutiliRepo roleutiliRepo) {
        this.roleutiliRepo = roleutiliRepo;
    }
    public void delete(Keyroleutili id)
    {
        roleutiliRepo.deleteById(id);
    }
    public void add(Roleutili r)
    {
        roleutiliRepo.save(r);
    }
    public Roleutili findbykey(Keyroleutili k)
    {
        return roleutiliRepo.findByKey(k);
    }
    public Boolean existbyket(Keyroleutili k)
    {
        return roleutiliRepo.existsByKey(k);
    }
}
