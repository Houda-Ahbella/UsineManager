package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.Probleme_qualite;
import com.fstm.ilisi.project.UsineManager.model.BO.Step;
import com.fstm.ilisi.project.UsineManager.model.DBA.ProblemequaliteRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProblemeQualiteService {
    private final ProblemequaliteRepo prbqualiterepo;

    public ProblemeQualiteService(ProblemequaliteRepo prbqualiterepo)
    {
        this.prbqualiterepo = prbqualiterepo;
    }
    public Probleme_qualite addPrb(Probleme_qualite prb)
    {

        return prbqualiterepo.save(prb);
    }
    public List<Probleme_qualite> findAllSteps() {
        return prbqualiterepo.findAll();
    }
    public Probleme_qualite update(Probleme_qualite prb)
    {
        if(prbqualiterepo.existsByDesignation(prb.getDesignation()))
        {
            Probleme_qualite p = new Probleme_qualite();
            p.setId(-1);
            return p;
        }
        return prbqualiterepo.save(prb);
    }
    public Probleme_qualite getbyId(int id)
    {
        return prbqualiterepo.getById(id);
    }

}
