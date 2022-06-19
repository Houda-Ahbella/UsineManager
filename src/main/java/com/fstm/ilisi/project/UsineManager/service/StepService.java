package com.fstm.ilisi.project.UsineManager.service;
import com.fstm.ilisi.project.UsineManager.exception.UserNotFoundException;
import com.fstm.ilisi.project.UsineManager.model.BO.Step;
import com.fstm.ilisi.project.UsineManager.model.DBA.StepRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StepService {
    private final StepRepo steprepo;

    @Autowired
    public StepService(StepRepo steprepo) {
        this.steprepo = steprepo;
    }

    public Step addStep(Step step) {

        return steprepo.save(step);
    }

    public List<Step> findAllSteps() {
        return steprepo.findAll();
    }
    public Step findbyde ( String des )
    {
        return steprepo.findByDes(des);
    }




    public Step updateStep(Step step) {
        return steprepo.save(step);
    }

    public Step findStepById(int id) {
        return steprepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteStep(int id){
        steprepo.deleteById(id);
    }
    public boolean existBydes(String des)
    {
        return  steprepo.existsByDes(des);
    }
    public boolean existById(int id)
    {
        return  steprepo.existsById(id);
    }


}
