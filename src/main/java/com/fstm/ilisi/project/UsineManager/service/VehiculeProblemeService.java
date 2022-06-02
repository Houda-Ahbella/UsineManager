package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.KeyProblemveh;
import com.fstm.ilisi.project.UsineManager.model.BO.Probleme_qualite;
import com.fstm.ilisi.project.UsineManager.model.BO.VehiculeProbleme;
import com.fstm.ilisi.project.UsineManager.model.DBA.VehiculeproblemeRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class VehiculeProblemeService implements Serializable {
    private final VehiculeproblemeRepo veprbrepo;

    public VehiculeProblemeService(VehiculeproblemeRepo veprbrepo) {
        this.veprbrepo = veprbrepo;
    }
    public VehiculeProbleme add(VehiculeProbleme VPR)
    {
        return veprbrepo.save(VPR);
    }
    public List<VehiculeProbleme> findall()
    {
     return veprbrepo.findAll();
    }
    public void delete(KeyProblemveh key)
    {
        veprbrepo.deleteById(key);
    }
    public int CountPr(Probleme_qualite pq)
    {
        return veprbrepo.countAllByPrb(pq);
    }
}
