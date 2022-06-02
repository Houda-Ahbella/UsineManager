package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.*;
import com.fstm.ilisi.project.UsineManager.model.DBA.Fin_EtapeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class Fin_EtapeService implements Serializable {
    private final Fin_EtapeRepo finetaperepo;
     @Autowired
    public Fin_EtapeService(Fin_EtapeRepo finetaperepo) {
        this.finetaperepo = finetaperepo;
    }
    public void Ajout(Vehicule vehicule, Marque marque)
    {
        for(ME_Organisation me : marque.getSteps() )
        {   System.out.println(me);
            Fin_Etape etape = new Fin_Etape();
            KeyvehiculeEtape key = new KeyvehiculeEtape();
            key.setVehiculeId(vehicule.getNum_Chassis());
            key.setStepId(me.getStep().getId());
            etape.setKey(key);
            etape.setVehicule(vehicule);
            etape.setStep(me.getStep());
            etape.setOrdre(me.getOrdre());
            etape.setEtat("En cours");
            finetaperepo.save(etape);
        }
    }
    public Fin_Etape update(Fin_Etape fe)
    {

        return finetaperepo.save(fe);
    }
    public List<Fin_Etape> findAll()
    {
        return finetaperepo.findAll();
    }
    public void delete(Fin_Etape fe)
    {
        finetaperepo.delete(fe);
    }
}
