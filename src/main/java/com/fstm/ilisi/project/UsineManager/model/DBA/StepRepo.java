package com.fstm.ilisi.project.UsineManager.model.DBA;


import com.fstm.ilisi.project.UsineManager.model.BO.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepo extends JpaRepository<Step, Integer> {
    Step findByDes(String Des);
    boolean existsByDes(String Des);

}
