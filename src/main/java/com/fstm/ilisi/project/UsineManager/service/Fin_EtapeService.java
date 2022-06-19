package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.model.BO.*;
import com.fstm.ilisi.project.UsineManager.model.DBA.Fin_EtapeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

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
      List<Fin_Etape> lista = finetaperepo.findByOrdreLessThanAndVehicule(fe.getOrdre(),fe.getVehicule());
      if(lista.size()>0)
      {   Collections.sort(lista, Fin_Etape.ComparatorOrder);
          if(lista.get(lista.size()-1).getDatefin().compareTo(fe.getDatefin())>0)
          {
              Fin_Etape e = new Fin_Etape();
              e.setEtat("La date doit etre supérieur à la date  " +lista.get(lista.size()-1).getDatefin());
              e.setOrdre(-1);
              return e;
          }
          else if(lista.get(lista.size()-1).getEtat().equals("Bloqué"))
          {
              Fin_Etape e = new Fin_Etape();
              e.setEtat("L'étape " + lista.get(lista.size()-1).getNomStep() +" est bloqué \n il faut la débloquer tout d'abord");
              e.setOrdre(-1);
              return e;
          }
          else
          {
              return finetaperepo.save(fe);
          }
      }
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

    public int count_today_step(Step step, String e, LocalDate date)
    {
        return  finetaperepo.countByStepAndEtatAndDatefin(step , e, date);
    }
    public int count_today_etat(String s , LocalDate d)
    {
        return  finetaperepo.countFin_Etape(s, d);
    }
    public int count_total_etat(String s)
    {
        return finetaperepo.countDistinctByEtat(s);
    }
    public  int countByStepAndEtat(Step st, String et)
    {
        return finetaperepo.countByStepAndEtat(st,et);
    }
    public List<Integer> Count_livree_by_month(Step step, String etat )
    {
        List<Fin_Etape> lista = finetaperepo.findAllByStepAndEtat(step , etat );
        List<Integer> yearvalues = new ArrayList<>();
        for (int i = 0 ; i<12 ; i++) yearvalues.add(0);
        for (Fin_Etape f : lista)
        {
            if(f.getDatefin().getYear()== Year.now().getValue())
            {
                if(f.getDatefin().getMonthValue()== 1){ yearvalues.set(0, yearvalues.get(0)+1);}

                else if(f.getDatefin().getMonthValue()==2){ yearvalues.set(1, yearvalues.get(1)+1);}
                else if(f.getDatefin().getMonthValue()==3){ yearvalues.set(2, yearvalues.get(2)+1);}
                else if(f.getDatefin().getMonthValue()==4){ yearvalues.set(3, yearvalues.get(3)+1);}
                else if(f.getDatefin().getMonthValue()==5){ yearvalues.set(4, yearvalues.get(4)+1);}
                else if(f.getDatefin().getMonthValue()==6){ yearvalues.set(5, yearvalues.get(5)+1);}
                else if(f.getDatefin().getMonthValue()==7){ yearvalues.set(6, yearvalues.get(6)+1);}
                else if(f.getDatefin().getMonthValue()==8){ yearvalues.set(7, yearvalues.get(7)+1);}
                else if(f.getDatefin().getMonthValue()==9){ yearvalues.set(8, yearvalues.get(8)+1);}
                else if(f.getDatefin().getMonthValue()==10){ yearvalues.set(9, yearvalues.get(9)+1);}
                else if(f.getDatefin().getMonthValue()==11){ yearvalues.set(10, yearvalues.get(10)+1);}
                else if(f.getDatefin().getMonthValue()==12){ yearvalues.set(11, yearvalues.get(11)+1);}
                else{}
            }
        }
        return yearvalues;
    }
    public List<Fin_Etape> findybystepandetatandve(  Vehicule ve, Collection<String> etats)
    {
        return finetaperepo.findByVehiculeAndEtatIn( ve , etats);
    }
    public List<Fin_Etape> findybydetat(String et)
    {
        return finetaperepo.findAllByEtat(et);
    }
   public List<Fin_Etape> findByOrdreLessThan(int ordre, Vehicule ve)
    {
        return finetaperepo.findByOrdreLessThanAndVehicule(ordre , ve);
    }
}
