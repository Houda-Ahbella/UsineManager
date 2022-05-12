package com.fstm.ilisi.project.UsineManager.service;
import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.Marque;
import com.fstm.ilisi.project.UsineManager.model.DBA.MarqueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class MarqueService {
    private final MarqueRepo marquerepo;

    @Autowired
    public MarqueService(MarqueRepo marquerepo) {
        this.marquerepo = marquerepo;
    }

    public Marque addMarque(Marque marque) {

        return marquerepo.save(marque);
    }

    public List<Marque> findAllMarques() {
        return marquerepo.findAll();
    }

    public Marque updateMarque(Marque marque) {
        return marquerepo.save(marque);
    }

    public Marque findMarqueById(String id) {
        return marquerepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteMarque(String id){
        marquerepo.deleteById(id);
    }
}
