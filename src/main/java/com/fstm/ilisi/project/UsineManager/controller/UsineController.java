package com.fstm.ilisi.project.UsineManager.controller;
import com.fstm.ilisi.project.UsineManager.model.BO.*;
import com.fstm.ilisi.project.UsineManager.service.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
    public UsineController(EmployeeService employeeService, MarqueService marqueservice, ModeleService modeleservice, LotService lotService, VehiculeService vehiculeservice) {
        this.employeeService = employeeService;
        this.marqueservice = marqueservice;
        this.modeleservice = modeleservice;
        this.lotService = lotService;
        this.vehiculeservice = vehiculeservice;
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
    public ResponseEntity<Set<Modele>> getModelsOfMarques (@PathVariable("id") String id) {
        Marque marque = marqueservice.findMarqueById(id);
        Set<Modele> models =  marque.getModeles();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }
    // Vehicules of Lot
    @GetMapping("/allvehiculesOfLot/{id}")
    public ResponseEntity<List<Vehicule>> getModelsOfMarques (@PathVariable("id") int id)
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
     /*************************Partie de recherche ***********************************/
     //
    @GetMapping("/findemployee/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @GetMapping("/findmarque/{id}")
    public ResponseEntity<Marque> getMarqueById (@PathVariable("id") String id) {
        Marque marque = marqueservice.findMarqueById(id);
        return new ResponseEntity<>(marque, HttpStatus.OK);
    }
    //Modele
    @GetMapping("/findmodele/{id}")
    public ResponseEntity<Modele> getModeleById (@PathVariable("id") String id) {
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
        System.out.println(modele);
        Modele newModele = modeleservice.addModele(modele);
        return new ResponseEntity<>(newModele, HttpStatus.CREATED);
    }
    // Marque
    @PostMapping("/addmarque")
    public ResponseEntity<Marque> addMarque(@RequestBody Marque marque) {
        System.out.println(marque);
        Marque newMarque = marqueservice.addMarque(marque);
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
    // ajouter lot avec fichier excel
    @PostMapping("/addCompleteLot")
    public ResponseEntity<Lot>GetCreatelotPage(@RequestBody ExcelReader reader)
    {

       Lot l=new Lot();
       Modele m = new Modele();
        Set<Vehicule> vehicules = new HashSet<Vehicule>();
       readfile(l,m,reader,vehicules);
       Modele modele=modeleservice.findModeleById(m.getDesignation());
       DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
       String formattedDayeTime = formatter.format(LocalDateTime.now());
       l.setDate_Entree(formattedDayeTime);
        l.setNombre_vehicules(vehicules.size());
       Lot newlot= lotService.addLot(l);
        l.setVehicules(vehicules);
        l.setModeleLot(modele);
       for (Vehicule v : l.getVehicules()) vehiculeservice.addVehicule(v);
       System.out.println(newlot);
        return new ResponseEntity<>(newlot, HttpStatus.CREATED);




    }
    public void readfile(Lot lot , Modele modele, ExcelReader r,Set<Vehicule> vehicules)
    {
        r.ChangeChemin();
        System.out.println(r.getChemin());
        File file = new File(r.getChemin());   //creating a new file instance
        FileInputStream fis = null;   //obtaining bytes from the file
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //creating Workbook instance that refers to .xlsx file

        try {


            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            itr.next();
            System.out.println("debut ************");
            for(int i=2;i<6; i++) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                Cell cell = cellIterator.next();
                System.out.println("debut again ************");
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





        }  catch (IOException e) {
            e.printStackTrace();
        }

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
        System.out.println("***************" + marque);
        Marque updateMarque = marqueservice.updateMarque(marque);
        return new ResponseEntity<>(updateMarque, HttpStatus.OK);
    }
    //Modele
    @PutMapping("/updateemodele")
    public ResponseEntity<Modele> updateModele(@RequestBody Modele modele) {
        Modele updatemodele = modeleservice.updateModele(modele);
        return new ResponseEntity<>(updatemodele, HttpStatus.OK);
    }
    @PutMapping("/updatevehicule")
    public ResponseEntity<Vehicule> updateVehicule(@RequestBody Vehicule ve) {
        System.out.println(ve);
        Vehicule updateve = vehiculeservice.updateVehicule(ve);
        System.out.println(updateve);
        return new ResponseEntity<>(updateve, HttpStatus.OK);
    }
    @PutMapping("/updatelot")
    public ResponseEntity<Lot> updateLot(@RequestBody Lot lot) {
        System.out.println(lot);
        Lot uplot = lotService.addLot(lot);
        return new ResponseEntity<>(uplot, HttpStatus.OK);
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
    public ResponseEntity<?> deleteMarque(@PathVariable("id") String id) {
       marqueservice.deleteMarque(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //Modele
    @DeleteMapping("/deletemodele/{id}")
    public ResponseEntity<?> deleteModele(@PathVariable("id") String id) {
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

}
