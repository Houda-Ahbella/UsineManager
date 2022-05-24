package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Modele;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeleRepo extends JpaRepository<Modele,Integer>
{
    boolean existsByDesignation(String des);
    Modele findByDesignation(String des);
}
