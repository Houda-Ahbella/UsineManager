package com.fstm.ilisi.project.UsineManager.controller;
import com.fstm.ilisi.project.UsineManager.model.BO.*;
import com.fstm.ilisi.project.UsineManager.service.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.*;

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
    public UsineController(EmployeeService employeeService, MarqueService marqueservice, ModeleService modeleservice, LotService lotService, VehiculeService vehiculeservice, StepService stepservice, ME_Organisation_Service me_orgaservice) {
        this.employeeService = employeeService;
        this.marqueservice = marqueservice;
        this.modeleservice = modeleservice;
        this.lotService = lotService;
        this.vehiculeservice = vehiculeservice;
        this.stepservice = stepservice;
        this.me_orgaservice = me_orgaservice;
    }
        /***************Partie de selection de toutes ***************/
    @GetMapping("/allemployee")
    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    // Marques
    @GetMapping("/allmarques")
    public ResponseEntity<List<Marque>> getAllMarques () {
        List<Marque> marques = marqueservice.findAllMarques();
        return new ResponseEntity<>(marques, HttpStatus.OK);
    }
    // Modele
    @GetMapping("/allmodeles")
    public ResponseEntity<List<Modele>> getAllModeles () {
        List<Modele> models = modeleservice.findAllModeles();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
    // Models of marques
    @GetMapping("/allmodelesOfMarque/{id}")
    public ResponseEntity<Set<Modele>> getModelsOfMarques (@PathVariable("id") int id) {
        Marque marque = marqueservice.findMarqueById(id);
        Set<Modele> models =  marque.getModeles();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
    // Vehicules of Lot
    @GetMapping("/allvehiculesOfLot/{id}")
    public ResponseEntity<List<Vehicule>> getvehiculesOfLot(@PathVariable("id") int id)
    {
        Lot lot = lotService.findLotById(id);
        List<Vehicule> OrderVehicules = new ArrayList<>(lot.getVehicules());
        Collections.sort(OrderVehicules, Vehicule.ComparatorOrder);
        return new ResponseEntity<>(OrderVehicules,HttpStatus.OK);

    }
    // vehicules
    @GetMapping("/allvehicules")
    public ResponseEntity<List<Vehicule>> getAllVehicules () {
        List<Vehicule> vehicules = vehiculeservice.findAllVehicules();
        return new ResponseEntity<>(vehicules, HttpStatus.OK);
    }
    // lots
    @GetMapping("/allLot")
    public ResponseEntity<List<Lot>> getAllLots () {
        List<Lot> lots = lotService.findAllLots();
        Collections.sort(lots, Lot.ComparatorOrder);
        Collections.reverse(lots);
        return new ResponseEntity<>(lots, HttpStatus.OK);
    }
    // Etapes
    @GetMapping("/allEtapes")
    public ResponseEntity<List<Step>> getAlletapes () {
        List<Step> steps = stepservice.findAllSteps();
        return new ResponseEntity<>(steps, HttpStatus.OK);
    }
    @GetMapping("/allOrga")
    public ResponseEntity<List<ME_Organisation>> getAllORGA () {
        List<ME_Organisation> em = me_orgaservice.findAllEtapesMarque();
        Collections.sort(em, ME_Organisation.ComparatorOrder);
        Collections.reverse(em);
        return new ResponseEntity<>(em, HttpStatus.OK);
    }
    @GetMapping("/allStepsofmarque/{id}")
    public ResponseEntity<Set<ME_Organisation>> getAllStepsOfmarque (@PathVariable("id") int id) {
        Set<ME_Organisation> em = marqueservice.findMarqueById(id).getSteps();
        return new ResponseEntity<>(em, HttpStatus.OK);
    }

    @GetMapping("/allEtapesNotINmarque/{id}")
    public  ResponseEntity<List<Step>> getallEtapesNotINmarque (@PathVariable("id") int id)
    {
        List<Step> steps = stepservice.findAllSteps();
        Set<ME_Organisation> marquestep = marqueservice.findMarqueById(id).getSteps();


            for (ME_Organisation ME : marquestep)
            {
                for(int i=0 ; i< steps.size() ; i++) {
                    if (ME.getId().getStepId() == steps.get(i).getId()) {
                        steps.remove(steps.get(i));
                    }
                }

            }




        return new ResponseEntity<>(steps, HttpStatus.OK);

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
        System.out.println(ve);
        Lot lot = lotService.findLotById(ve.getLot().getNum_lot());
        lot.setNombre_vehicules(lot.getNombre_vehicules()+1);
        Vehicule newVE = vehiculeservice.addVehicule(ve);
        return new ResponseEntity<>(newVE, HttpStatus.CREATED);
    }
    // Lot
    @PostMapping("/addlot")
    public ResponseEntity<Lot> addMarque(@RequestBody Lot lot) {
        System.out.println(lot);
        Lot newlot = lotService.addLot(lot);
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

      /*
      Modele modele=modeleservice.findbydes(m.getDesignation());
       DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
       String formattedDayeTime = formatter.format(LocalDateTime.now());
       l.setDate_Entree(formattedDayeTime);
        l.setNombre_vehicules(vehicules.size());
       Lot newlot= lotService.addLot(l);
        l.setVehicules(vehicules);
        l.setModeleLot(modele);
      for (Vehicule v : l.getVehicules()) vehiculeservice.addVehicule(v);
       System.out.println(newlot);
       */
        return new ResponseEntity<>(l, HttpStatus.CREATED);

    }
        /*
        Iterator<Row> itr = sheet.iterator();
        itr.next();
        System.out.println("debut ************");
        for(int i=2;i<6; i++) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            Cell cell = cellIterator.next();
            System.out.println("debut again ************");
            System.out.println(cell.getStringCellValue());
            if (cell.getCellType() == CellType.STRING) {
                System.out.println(cell.getStringCellValue());
                if (cell.getStringCellValue().equals("MODELE")) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING)
                    {
                        System.out.println(" modele "+cell.getStringCellValue());
                        modele.setDesignation(cell.getStringCellValue());
                    }
                }
                else if (cell.getStringCellValue().equals("BATCH NO.")) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING) {
                        System.out.println(" BATCH NO. "+cell.getStringCellValue());
                        lot.setNum_bach(cell.getStringCellValue());
                    }
                } else if (cell.getStringCellValue().equals("CONNAISSEMENT")) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.STRING) {
                        System.out.println(" CONNAISSEMENT "+cell.getStringCellValue());
                        lot.setConnaissement(cell.getStringCellValue());
                    }
                } else if (cell.getStringCellValue().equals("N° LOT")) {
                    cell = cellIterator.next();
                    if (cell.getCellType() == CellType.NUMERIC)
                    {
                        System.out.println(" N° LOT "+cell.getNumericCellValue());
                        lot.setNum_lot((int) cell.getNumericCellValue());
                    }

                } else System.out.println(cell.getStringCellValue());


            }


        }

         itr.next();
        while (itr.hasNext())
        {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
            int i = 0;
            Vehicule vehicule = new Vehicule();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if(i==0)
                {
                    if (cell.getCellType() == CellType.NUMERIC)
                        vehicule.setOrdre((int)cell.getNumericCellValue());
                }
                else if(i==1) {
                    if (cell.getCellType() == CellType.STRING)
                        vehicule.setNum_Chassis(cell.getStringCellValue());
                }
                else if(i==2) {
                    if (cell.getCellType() == CellType.STRING)
                        vehicule.setNum_Engine(cell.getStringCellValue());
                }
                else if(i==3)
                {
                    if (cell.getCellType() == CellType.STRING)
                        vehicule.setCouleur(cell.getStringCellValue());
                }

                i++;


            }
            vehicule.setLot(lot);
            vehicules.add(vehicule);


        }

        System.out.println(lot);





         */



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
        System.out.println(updateve);
        return new ResponseEntity<>(updateve, HttpStatus.OK);
    }
    //lot
    @PutMapping("/updatelot")
    public ResponseEntity<Lot> updateLot(@RequestBody Lot lot) {
        System.out.println(lot);
        Lot uplot = lotService.addLot(lot);
        return new ResponseEntity<>(uplot, HttpStatus.OK);
    }
    //ME_Organisation
    @PutMapping("/updateStepOfMarque")
    public ResponseEntity<ME_Organisation> updateME_Orga(@RequestBody ME_Organisation ME)
    {

        if(stepservice.existBydes(ME.getStep().getDes()))
        {
            ME= new ME_Organisation();
            ME.setOrdre(-1);
        }
        else if(me_orgaservice.exitByOrdre(ME.getOrdre()))
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
        lotService.updateLot(lot);
        vehiculeservice.deleteVehicule(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Lot
    @DeleteMapping("/deletelot/{id}")
    public ResponseEntity<?> deleteLot(@PathVariable("id") int id) {
        Lot lot = lotService.findLotById(id) ;
        for ( Vehicule v : lot.getVehicules()) vehiculeservice.deleteVehicule(v.getNum_Chassis());
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
}
