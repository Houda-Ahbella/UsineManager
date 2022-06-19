package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.Role;
import com.fstm.ilisi.project.UsineManager.model.DBA.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RoleService {
    private final RoleRepo roleRepo;
      @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }
    public Role find(int r)
    {
        return roleRepo.findById(r).get();
    }
}
