package com.fstm.ilisi.project.UsineManager.controller;
import com.fstm.ilisi.project.UsineManager.model.BO.*;
import com.fstm.ilisi.project.UsineManager.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/Usine")
@CrossOrigin
public class UsineController {
    private final EmployeeService employeeService;
    private final MarqueService marqueservice;
    private final ModeleService modeleservice;
    private final LotService lotService;
    private final VehiculeService vehiculeservice;
    private final StepService stepservice;
    private final ME_Organisation_Service me_orgaservice;
    private final Fin_EtapeService fin_etapeservice;
    private final ProblemeQualiteService problemequaliteservice;
    private final VehiculeProblemeService vehiculeProblemeService;
    private final UtlisateurService utilisateursevice;
    private final CompteService compteService;
    private final RoleutiliService roleutiliService;
    private final RoleService roleservice;
    public UsineController(EmployeeService employeeService, MarqueService marqueservice, ModeleService modeleservice, LotService lotService, VehiculeService vehiculeservice, StepService stepservice, ME_Organisation_Service me_orgaservice, Fin_EtapeService fin_etapeservice, ProblemeQualiteService problemequaliteservice, VehiculeProblemeService vehiculeProblemeService, UtlisateurService utilisateursevice, CompteService compteService, RoleutiliService roleutiliService, RoleutiliService roleservice, RoleService roleservice1) {
        this.employeeService = employeeService;
        this.marqueservice = marqueservice;
        this.modeleservice = modeleservice;
        this.lotService = lotService;
        this.vehiculeservice = vehiculeservice;
        this.stepservice = stepservice;
        this.me_orgaservice = me_orgaservice;
        this.fin_etapeservice = fin_etapeservice;
        this.problemequaliteservice = problemequaliteservice;
        this.vehiculeProblemeService = vehiculeProblemeService;
        this.utilisateursevice = utilisateursevice;
        this.compteService = compteService;
        this.roleutiliService = roleutiliService;
        this.roleservice = roleservice1;
    }

    /***************Partie de selection de toutes ***************/
    @GetMapping("/allemployee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Marques
    @GetMapping("/allmarques")
    public ResponseEntity<List<Marque>> getAllMarques() throws ParseException {
        List<Marque> marques = marqueservice.findAllMarques();
        return new ResponseEntity<>(marques, HttpStatus.OK);
    }

    // Modele
    @GetMapping("/allmodeles")
    public ResponseEntity<List<Modele>> getAllModeles() {
        List<Modele> models = modeleservice.findAllModeles();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    // Models of marques
    @GetMapping("/allmodelesOfMarque/{id}")
    public ResponseEntity<Set<Modele>> getModelsOfMarques(@PathVariable("id") int id) {
        Marque marque = marqueservice.findMarqueById(id);
        Set<Modele> models = marque.getModeles();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    // Vehicules of Lot
    @GetMapping("/allvehiculesOfLot/{id}")
    public ResponseEntity<List<Vehicule>> getvehiculesOfLot(@PathVariable("id") int id) {
        Lot lot = lotService.findLotById(id);
        List<Vehicule> OrderVehicules = new ArrayList<>(lot.getVehicules());
        Collections.sort(OrderVehicules, Vehicule.ComparatorOrder);
        return new ResponseEntity<>(OrderVehicules, HttpStatus.OK);

    }

    @GetMapping("/allVehiculesbloquéOfLot/{id}")
    public ResponseEntity<List<Vehicule>> allVehiculesbloquéOfLot(@PathVariable("id") int id) {
        List<Vehicule> ves = vehiculeservice.findAllBloque(lotService.findLotById(id));
        return new ResponseEntity<>(ves, HttpStatus.OK);
    }


    // vehicules
    @GetMapping("/allvehicules")
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        List<Vehicule> vehicules = vehiculeservice.findAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }

    // lots
    @GetMapping("/allLot")
    public ResponseEntity<List<Lot>> getAllLots() {
        List<Lot> lots = lotService.findAllLots();
        Collections.sort(lots, Lot.ComparatorOrder);
        Collections.reverse(lots);
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }

    // Etapes
    @GetMapping("/allEtapes")
    public ResponseEntity<List<Step>> getAlletapes() {
        List<Step> steps = stepservice.findAllSteps();
        return new ResponseEntity<>(steps, HttpStatus.OK);
    }

    @GetMapping("/allOrga")
    public ResponseEntity<List<ME_Organisation>> getAllORGA() {
        List<ME_Organisation> em = me_orgaservice.findAllEtapesMarque();
        Collections.sort(em, ME_Organisation.ComparatorOrder);
        Collections.reverse(em);
        return new ResponseEntity<>(em, HttpStatus.OK);
    }

    @GetMapping("/allStepsofmarque/{id}")
    public ResponseEntity<Set<ME_Organisation>> getAllStepsOfmarque(@PathVariable("id") int id) {
        Set<ME_Organisation> em = marqueservice.findMarqueById(id).getSteps();
        return new ResponseEntity<>(em, HttpStatus.OK);
    }

    @GetMapping("/allEtapesNotINmarque/{id}")
    public ResponseEntity<List<Step>> getallEtapesNotINmarque(@PathVariable("id") int id) {
        List<Step> steps = stepservice.findAllSteps();
        Set<ME_Organisation> marquestep = marqueservice.findMarqueById(id).getSteps();


        for (ME_Organisation ME : marquestep) {
            for (int i = 0; i < steps.size(); i++) {
                if (ME.getId().getStepId() == steps.get(i).getId()) {
                    steps.remove(steps.get(i));
                }
            }

        }


        return new ResponseEntity<>(steps, HttpStatus.OK);

    }

    @GetMapping("/allEtapeOfvehiculewithetet/{id}")
    public ResponseEntity<List<Fin_Etape>> getallEtapeOfvehiculewithetet(@PathVariable("id") String id)
    {
        Vehicule ve = vehiculeservice.findVehiculeById(id);
        Collection<String> etats = new ArrayList<>();
        etats.add("Fini"); etats.add("Bloqué");

        List<Fin_Etape> list1= fin_etapeservice.findybystepandetatandve(ve, etats);
        Collections.sort(list1, Fin_Etape.ComparatorOrder);
        System.out.println(list1);
        return  new ResponseEntity<>(list1, HttpStatus.OK);

    }
    @GetMapping("/allFinEtape")
    public ResponseEntity<List<Fin_Etape>> getallFinEtape()
    {  List<Fin_Etape>  finis = fin_etapeservice.findAll();
        return new ResponseEntity<>(finis,HttpStatus.OK);
    }
    @GetMapping("/allProblemes")
    public ResponseEntity<List<Probleme_qualite>> getallProblemes()
    {
        List<Probleme_qualite> prbs = problemequaliteservice.findAllSteps();
        for(Probleme_qualite p : prbs)
            p.setCount(vehiculeProblemeService.CountPr(p));
        return new ResponseEntity<>(prbs,HttpStatus.OK);

    }
    @GetMapping("/allProblemesOfLot/{lot}")
    public ResponseEntity<List<Probleme_qualite>> allProblemesOfLot(@PathVariable("lot") int lot )
    {
        Lot l = lotService.findLotById(lot);
        List<Probleme_qualite> prbs = new ArrayList<>();
         Set<Vehicule> vehicules = l.getVehicules();
         for (Vehicule v: vehicules)
         {
             for(VehiculeProbleme p : v.getProblemes())
             {
                 Probleme_qualite q = problemequaliteservice.getbyId(p.getKey().getProblemeId());

                 if(prbs.contains(q)){ q= prbs.get(prbs.indexOf(q)); q.setCount(q.getCount()+1); }
                 else { q.setCount(1); prbs.add(q);}
             }

         }
        return new ResponseEntity<>(prbs,HttpStatus.OK);

    }
    @GetMapping("/allVehiculesProblemes")
    public ResponseEntity<List<VehiculeProbleme>> allVehiculesProblemes()
    {
        List<VehiculeProbleme> prbs = vehiculeProblemeService.findall();
        return new ResponseEntity<>(prbs,HttpStatus.OK);
    }
    @GetMapping("/StatistiqueQuotidienne")
    public ResponseEntity<List<Integer>> StatistiqueQuotidienne()
    {   List<Integer> l = new ArrayList();
        Step steplivre = stepservice.findbyde("LIVRAISON");
        int livrees = fin_etapeservice.count_today_step(steplivre,"Fini",LocalDate.now() );
        int bloque = fin_etapeservice.count_today_etat("Bloqué" , LocalDate.now());
        int en =  fin_etapeservice.count_total_etat("En cours") - fin_etapeservice.count_total_etat("Bloqué")
                - fin_etapeservice.countByStepAndEtat(steplivre,"Fini");
        l.add(livrees); l.add(bloque); l.add(en);
        return new ResponseEntity<List<Integer>>(l, HttpStatus.OK);
    }
    @GetMapping("/Statistiqueparmonth")
    public ResponseEntity<List<Integer>> Statistiqueparmonth()
    {
        Step step = stepservice.findbyde("LIVRAISON");
        List<Integer> i = fin_etapeservice.Count_livree_by_month(step,"Fini");
        return new ResponseEntity<>(i,HttpStatus.OK);
    }
     @GetMapping("/Allutilisateur")
     public ResponseEntity<List<Utilisateur>> Allutilisateur()
     {
         List<Utilisateur> utilisateurs = utilisateursevice.findAll();
         return new ResponseEntity<>(utilisateurs,HttpStatus.OK);
     }
     /*************************Partie de recherche ***********************************/
     //
    @GetMapping("/findemployee/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @GetMapping("/findmarque/{id}")
    public ResponseEntity<Marque> getMarqueById (@PathVariable("id") int id) {
        Marque marque = marqueservice.findMarqueById(id);
        return new ResponseEntity<>(marque, HttpStatus.OK);
    }
    //Modele
    @GetMapping("/findmodele/{id}")
    public ResponseEntity<Modele> getModeleById (@PathVariable("id") int id) {
        Modele modele = modeleservice.findModeleById(id);
        return new ResponseEntity<>(modele, HttpStatus.OK);
    }
    //Vehicule
    @GetMapping("/findvehicule/{id}")
    public ResponseEntity<List<Vehicule>> getVehiculeById (@PathVariable("id") String id) {
        Vehicule ve = vehiculeservice.findVehiculeById(id);
        List<Vehicule> ves = new ArrayList<Vehicule>();
        ves.add(ve);
        return new ResponseEntity<>(ves, HttpStatus.OK);
    }
    //Lot
    @GetMapping("/findlot/{id}")
    public ResponseEntity<List<Lot>> getLotById (@PathVariable("id") int id) {
        Lot lot = lotService.findLotById(id);
        List<Lot> lots = new ArrayList<Lot>();
        lots.add(lot);
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
    @PostMapping("/findUtilisateur")
    public ResponseEntity<Utilisateur> findUtilisateur(@RequestBody Compte id)
    {

        Utilisateur u = compteService.FindByid(id);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @GetMapping("/findUtilisateurbyid/{id}")
    public ResponseEntity<Utilisateur> findUtilisateurbyid(@PathVariable("id") int id)
    {

        Utilisateur u = utilisateursevice.findbyid(id);

        return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @GetMapping("/findUtilisateurbyNom/{id}")
    public ResponseEntity<List<Utilisateur>> ffindUtilisateurbyNom(@PathVariable("id") String id)
    {

        List<Utilisateur> u = utilisateursevice.findbynom(id);

        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    /********************Partie d'ajout ************************/
    //
    @PostMapping("/addemployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    // Modele
    @PostMapping("/addmodele")
    public ResponseEntity<Modele> addModele(@RequestBody Modele modele) {
        Modele newModele = modeleservice.addModele(modele);
        if(newModele==null) newModele = new Modele();
        return new ResponseEntity<>(newModele, HttpStatus.CREATED);
    }
    // Marque
    @PostMapping("/addmarque")
    public ResponseEntity<Marque> addMarque(@RequestBody Marque marque) {
        Marque newMarque = marqueservice.addMarque(marque);
        if(newMarque==null) newMarque = new Marque();
        return new ResponseEntity<>(newMarque, HttpStatus.CREATED);
    }
    // Vehicule
    @PostMapping("/addvehicule")
    public ResponseEntity<Vehicule> addVehicule(@RequestBody Vehicule ve) {
        Lot lot = lotService.findLotById(ve.getLot().getNum_lot());
        lot.setNombre_vehicules(lot.getNombre_vehicules()+1);
        Vehicule newVE = vehiculeservice.addVehicule(ve);
        Marque marque = marqueservice.findMarqueById(ve.getModele().getMarque().getNum_marque());

        fin_etapeservice.Ajout(newVE , marque);
        return new ResponseEntity<>(newVE, HttpStatus.CREATED);
    }
    // Lot
    @PostMapping("/addlot")
    public ResponseEntity<Lot> addlot(@RequestBody List<Vehicule> vehicules) {
        System.out.println(vehicules);
        Lot newlot = lotService.addLot(vehicules.get(0).getLot());
         if(newlot==null)
         {
             newlot = new Lot();
             newlot.setNum_lot(-1);
         }
         else
         {
             Modele modele = modeleservice.findbydes(vehicules.get(0).getModele().getDesignation());
             int i =0;
             for (Vehicule v : vehicules)
             {
                 v.setLot(newlot);
                 v.setModele(modele);
                 Vehicule veh = vehiculeservice.addVehicule(v);
                 if(veh.getOrdre()>0)
                 {
                     newlot.getVehicules().add(v);

                     fin_etapeservice.Ajout(veh , modele.getMarque());
                     System.out.println(i);
                     i++;
                 }

             }
             if(i==0) {
                 lotService.deleteLot(newlot.getNum_lot());
                 newlot.setNum_lot(0);
             }
             else
             {
                 newlot.setNombre_vehicules(i);
                 lotService.updateLot(newlot);
             }

         }

        return new ResponseEntity<>(newlot, HttpStatus.CREATED);
    }
        // Lot
    @PostMapping("/addExisteLot")
    public ResponseEntity<Lot> addaddExisteLot(@RequestBody List<Vehicule> vehicules) {

        Lot newlot = lotService.findLotById(vehicules.get(0).getLot().getNum_lot());
        Modele modele = modeleservice.findbydes(vehicules.get(0).getModele().getDesignation());
        int i = 0;
            for (Vehicule v : vehicules)
            {
                v.setLot(newlot);
                v.setModele(modele);
                Vehicule veh = vehiculeservice.addVehicule(v);
                if(veh.getOrdre()>0)
                {
                    newlot.getVehicules().add(v);
                    i++;
                }

            }
          newlot.setNombre_vehicules(newlot.getNombre_vehicules()+i);
            lotService.updateLot(newlot);
        return new ResponseEntity<>(newlot, HttpStatus.CREATED);
    }

    // Ajouter des etapes d'une marque
    @PostMapping("/addStepINmarque")
    public ResponseEntity<ME_Organisation> addStepINmarque(@RequestBody ME_Organisation ME) {
        if(me_orgaservice.exitByOrdre(ME.getOrdre())==true)
        {
            ME=new ME_Organisation();
            ME.setOrdre(-2);
            return new ResponseEntity<>(ME, HttpStatus.CREATED);
        }
        else
        {
            ME.setMarque(marqueservice.findMarqueById(ME.getId().getMarqueId()));
            ME.setStep(stepservice.findStepById(ME.getId().getStepId()));
            ME_Organisation me = me_orgaservice.addEtapeMarque(ME);

            return new ResponseEntity<>(me, HttpStatus.CREATED);
        }

    }
    @PostMapping("/addNewStepINmarque")
    public ResponseEntity<ME_Organisation> addNewStepINmarque(@RequestBody ME_Organisation ME) {
        if(stepservice.existBydes(ME.getStep().getDes())==true)
        {

            ME.setOrdre(-1);
        }
        else if(me_orgaservice.exitByOrdre(ME.getOrdre()))
        {

            ME.setOrdre(-2);
        }
        else
        {
            ME.setMarque(marqueservice.findMarqueById(ME.getId().getMarqueId()));
            Step step = new Step();
            step.setDes(ME.getStep().getDes());
            Step newstep = stepservice.addStep(step);
            ME.setStep(newstep);
            ME_Organisation me = me_orgaservice.addEtapeMarque(ME);
        }

        return new ResponseEntity<>(ME, HttpStatus.CREATED);
    }

    // ajouter lot avec fichier excel
    @PostMapping("/addCompleteLot")
    public ResponseEntity<Lot>GetCreatelotPage(@RequestBody ExcelReader reader) throws IOException {
    System.out.println("reader" + reader.getExtension());
       Lot l=new Lot();
       Modele m = new Modele();
        Set<Vehicule> vehicules = new HashSet<Vehicule>();
        reader.readerfile(l,m,vehicules);
        System.out.println("vehicules "+ vehicules);
        return new ResponseEntity<>(l, HttpStatus.CREATED);

    }

   @PostMapping("/addVehiculeProbleme")
   public ResponseEntity<VehiculeProbleme> addVehiculeProbleme(@RequestBody VehiculeProbleme prb)
   {

       Vehicule ve = vehiculeservice.findVehiculeById(prb.getKey().getVehiculeId());
       prb.setVehicule(ve);
       Probleme_qualite pq = problemequaliteservice.getbyId(prb.getKey().getProblemeId());
       prb.setPrb(pq);
       VehiculeProbleme p = vehiculeProblemeService.add(prb);
       return  new ResponseEntity<>(p,HttpStatus.CREATED);
   }
   @PostMapping("/addUtilisateur")
   public ResponseEntity<Utilisateur> addUtilisateur(@RequestBody Utilisateur u)
   {
      Utilisateur a = new Utilisateur();
      a.setNom(u.getNom());
      a.setPrenom(u.getPrenom());
      Utilisateur utl = utilisateursevice.add(a);
      if(utl.getId()>0)
      {
          Compte c = new Compte();
          c.setEmail(u.getCompte().getEmail());
          c.setPassword(u.getCompte().getPassword());
          c.setUtilisateur(utl);
          Compte c1 = compteService.add(c);
          if(c1.getEmail().equals(""))
          {
              utilisateursevice.delete(utl);
              utl.setId(-2);

          }
          else
          {
              for (Roleutili r : u.getRoles())
              {
                  r.getKey().setUtlisateurId(utl.getId());
                  r.setRole(roleservice.find(r.getKey().getRoleId()));
                  r.setUtilisateur(utl);
                  roleutiliService.add(r);
              }

          }
      }
        return new ResponseEntity<>(utl,HttpStatus.CREATED);
   }
    /**********************Modification******************/
    //
    @PutMapping("/updateemployee")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
    //Marque
    @PutMapping("/updateemarque")
    public ResponseEntity<Marque> updateMarque(@RequestBody Marque marque) {
        Marque updateMarque = marqueservice.updateMarque(marque);
        return new ResponseEntity<>(updateMarque, HttpStatus.OK);
    }
    //Modele
    @PutMapping("/updateemodele")
    public ResponseEntity<Modele> updateModele(@RequestBody Modele modele) {
        Modele updatemodele = modeleservice.updateModele(modele);
        return new ResponseEntity<>(updatemodele, HttpStatus.OK);
    }
    //Vehicule
    @PutMapping("/updatevehicule")
    public ResponseEntity<Vehicule> updateVehicule(@RequestBody Vehicule ve) {
        System.out.println(ve);
        Vehicule updateve = vehiculeservice.updateVehicule(ve);
        return new ResponseEntity<>(updateve, HttpStatus.OK);
    }
    //lot
    @PutMapping("/updatelot")
    public ResponseEntity<Lot> updateLot(@RequestBody Lot lot) {
        System.out.println(lot);
        Lot uplot = lotService.updateLot(lot);
        return new ResponseEntity<>(uplot, HttpStatus.OK);
    }
    //ME_Organisation
    @PutMapping("/updateStepOfMarque")
    public ResponseEntity<ME_Organisation> updateME_Orga(@RequestBody ME_Organisation ME)
    {

        if(me_orgaservice.exitByOrdre(ME.getOrdre()))
        {
            ME= new ME_Organisation();
            ME.setOrdre(-2);
        }

        else
        {
            ME.setMarque(marqueservice.findMarqueById(ME.getId().getMarqueId()));
            Step step = new Step();
            step.setId(ME.getId().getStepId());
            step.setDes(ME.getStep().getDes());
            Step newstep = stepservice.updateStep(step);
            ME.setStep(newstep);
            ME_Organisation me = me_orgaservice.addEtapeMarque(ME);
        }
        return new ResponseEntity<>(ME, HttpStatus.CREATED);
    }
    //Fin Etape
    @PutMapping("/updateFinEtape")
    public ResponseEntity<Fin_Etape> updateFinEtape(@RequestBody Fin_Etape finetape)
    {
        Step step = stepservice.findStepById(finetape.getKey().getStepId());
        Vehicule ve = vehiculeservice.findVehiculeById(finetape.getKey().getVehiculeId());
        finetape.setStep(step);
        finetape.setVehicule(ve);

        Fin_Etape fe = fin_etapeservice.update(finetape);
           System.out.println(fe);
        return  new ResponseEntity<>(fe,HttpStatus.OK);
    }
    @PutMapping("/UpdateProbleme")
    public ResponseEntity<Probleme_qualite> UpdateProbleme(@RequestBody Probleme_qualite pq)
    {
        Probleme_qualite prbq = problemequaliteservice.update(pq);
        return new ResponseEntity<>(prbq,HttpStatus.CREATED);
    }
    @PutMapping("/UpdateUtilisateur")
    public ResponseEntity<Utilisateur> UpdateUtilisateur(@RequestBody Utilisateur u) {

        Utilisateur a = utilisateursevice.findbyid(u.getId());
        if(!utilisateursevice.exitsbynomandorenom(u.getNom(),u.getPrenom()))
        {
            a.setNom(u.getNom());
            a.setPrenom(u.getPrenom());
        }

        Compte c = compteService.findbyUser(a);

       if(!c.getPassword().equals(u.getCompte().getPassword()))
       {
           c.setPassword(u.getCompte().getPassword());


       }
       if(!c.getEmail().equals(u.getCompte().getEmail()))
       {
           if(!compteService.existbyid(u.getCompte().getEmail()))
           c.setEmail(u.getCompte().getEmail());


       }
        compteService.save(c);
        a.setCompte(c);
        Utilisateur utl = utilisateursevice.save(a);
        return new ResponseEntity<>(utl,HttpStatus.CREATED);

    }
    /************* la partie du suppression*******************/
    //
    @DeleteMapping("/deleteemployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {

        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Marque
    @DeleteMapping("/deletemarque/{id}")
    public ResponseEntity<?> deleteMarque(@PathVariable("id") int id) {
       marqueservice.deleteMarque(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Modele
    @DeleteMapping("/deletemodele/{id}")
    public ResponseEntity<?> deleteModele(@PathVariable("id") int id) {
        modeleservice.deleteModele(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Vehicule
    @DeleteMapping("/deletevehicule/{id}")
    public ResponseEntity<?> deleteVehicule(@PathVariable("id") String id) {
        Vehicule v = vehiculeservice.findVehiculeById(id);
        Lot lot = lotService.findLotById(v.getLot().getNum_lot());
        lot.setNombre_vehicules(lot.getNombre_vehicules()-1);
        for(Fin_Etape e : v.getSteps())
            fin_etapeservice.delete(e);
        for(VehiculeProbleme p : v.getProblemes())
            vehiculeProblemeService.delete(p.getKey());
        vehiculeservice.deleteVehicule(id);
        if(lot.getNombre_vehicules()==0)
            lotService.deleteLot(lot.getNum_lot());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Lot
    @DeleteMapping("/deletelot/{id}")
    public ResponseEntity<?> deleteLot(@PathVariable("id") int id) {
        Lot lot = lotService.findLotById(id) ;
        for ( Vehicule v : lot.getVehicules())
        {
            for(Fin_Etape e : v.getSteps())
                fin_etapeservice.delete(e);
            for(VehiculeProbleme p : v.getProblemes())
                vehiculeProblemeService.delete(p.getKey());
                 vehiculeservice.deleteVehicule(v.getNum_Chassis());


        }
        lotService.deleteLot(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/deleteEtapeFROMmarque/{id}/{ij}")
    public ResponseEntity<?> deleteEtapeFROMmarque(@PathVariable("id") int id, @PathVariable("ij") int ij)
    {  KeyEtapeMarque key = new KeyEtapeMarque();
        key.setStepId(id);
        key.setMarqueId(ij);
       me_orgaservice.deleteEtapeMarque(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/DeleteProblemeVehicule/{id}/{ij}")
    public ResponseEntity<?> DeleteProblemeVehicule(@PathVariable("id") String id, @PathVariable("ij") int ij)
    {
        KeyProblemveh key = new KeyProblemveh();
        key.setProblemeId(ij);
        key.setVehiculeId(id);
        System.out.println(key);
        vehiculeProblemeService.delete(key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/DeleteUtilisateur/{id}")
    public ResponseEntity<?> DeleteUtilisateur(@PathVariable("id") int id)
    {
        Utilisateur u = utilisateursevice.findbyid(id);
        Compte c = compteService.findbyUser(u);

        for(Roleutili r : u.getRoles())
        {
            Keyroleutili ke = new Keyroleutili();
            ke.setRoleId(r.getRole().getId());
            ke.setUtlisateurId(u.getId());
            roleutiliService.delete(ke);
        }
        compteService.delete(c);
        utilisateursevice.delete(u);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
