package com.fstm.ilisi.project.UsineManager.model.BO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ExcelReader {

    private String chemin;
    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public ExcelReader() {
    }
    public void ChangeChemin()
    {
             this.chemin.replace("\\","\\\\");
    }

    public ExcelReader(String chemin) {
        this.chemin = chemin;
    }
    public Lot ReadFie(String modele )
    {
        File file = new File(this.chemin);   //creating a new file instance
       Lot lot = new Lot();
        FileInputStream fis = null;   //obtaining bytes from the file
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //creating Workbook instance that refers to .xlsx file

        try {


            String color = "WHITE";
            Set<Vehicule> vehicules = new HashSet<Vehicule>();
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            Iterator<Row> itr = sheet.iterator();
            itr.next();

            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                int i = 0;
                Vehicule vehicule = new Vehicule();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (i == 1)
                        if (modele == null) {
                            if (cell.getCellType() == CellType.STRING) {
                                modele=cell.getStringCellValue().replace(" ","");
                                System.out.println("i m changing modele " + modele);

                            }
                        }
                    if (i == 2) {
                        if (lot.getNum_lot() == 0)
                            if (cell.getCellType() == CellType.NUMERIC) {
                                lot.setNum_lot((int) cell.getNumericCellValue());
                            }
                    }
                    if (i == 4)
                        if (cell.getCellType() == CellType.STRING)
                            vehicule.setNum_Chassis(cell.getStringCellValue());
                    if (i == 5)
                        //il faut faire les testes
                        if (cell.getCellType() == CellType.STRING)
                            vehicule.setNum_Engine(cell.getStringCellValue());
                    if (i == 6)
                        if (cell.getCellType() == CellType.STRING)
                            color = cell.getStringCellValue();

                    i++;


                }
                vehicule.setCouleur(color);
                vehicule.setLot(lot);
                vehicules.add(vehicule);

            }
            lot.setNombre_vehicules();
            lot.setVehicules(vehicules);
            System.out.println(lot);


        }  catch (IOException e) {
            e.printStackTrace();
        }
        return lot;
    }
}
