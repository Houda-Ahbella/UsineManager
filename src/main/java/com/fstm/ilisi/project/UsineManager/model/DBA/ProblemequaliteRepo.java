package com.fstm.ilisi.project.UsineManager.model.DBA;

import com.fstm.ilisi.project.UsineManager.model.BO.Probleme_qualite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemequaliteRepo extends JpaRepository<Probleme_qualite,Integer>
{
    boolean existsByDesignation(String des);
}
