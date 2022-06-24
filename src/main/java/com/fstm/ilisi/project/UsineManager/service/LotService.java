package com.fstm.ilisi.project.UsineManager.service;

import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.Lot;
import com.fstm.ilisi.project.UsineManager.model.BO.Marque;
import com.fstm.ilisi.project.UsineManager.model.DBA.LotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LotService {
    private final LotRepo lotrepo;
    @Autowired
    public LotService(LotRepo lotrepo) {
        this.lotrepo = lotrepo;
    }
    public Lot addLot(Lot lot)
    {
        if(lotrepo.existsById(lot.getNum_lot()))
            return null;
          return lotrepo.save(lot);
    }
    public List<Lot> findAllLots() {
        return lotrepo.findAll();
    }

    public Lot updateLot(Lot lot) {
        return lotrepo.save(lot);
    }

    public Lot findLotById(int id) {

        return lotrepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
    public Boolean existbyid(int id)
    {
        return lotrepo.existsById(id);
    }
    public void deleteLot(int id){
        lotrepo.deleteById(id);
    }
}
