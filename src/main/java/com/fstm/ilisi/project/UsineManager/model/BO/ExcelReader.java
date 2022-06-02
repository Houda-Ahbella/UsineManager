package com.fstm.ilisi.project.UsineManager.model.BO;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.STRING;

public class ExcelReader {

    private String chemin;
    public String getChemin() {
        return chemin;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    private String extension;
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
                            vehicule.setNumengine(cell.getStringCellValue());
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
    public void FirstInfo(Modele modele , Lot lot , Sheet guru99Sheet, FormulaEvaluator formulaEvaluator)
    {
        for(int i=1 ; i<6; i++)
        {
            Row row = guru99Sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++)
            {
                CellType ty = formulaEvaluator.evaluateInCell(row.getCell(j)).getCellType();
                if (ty == STRING) {
                    if (row.getCell(j).getStringCellValue().equals("MODELE"))
                        modele.setDesignation( row.getCell(j+1).getStringCellValue());
                    if (row.getCell(j).getStringCellValue().equals("BATCH NO."))
                        lot.setNum_bach(row.getCell(j+1).getStringCellValue());
                    if (row.getCell(j).getStringCellValue().equals("CONNAISSEMENT"))
                        lot.setConnaissement(row.getCell(j+1).getStringCellValue());
                    if (row.getCell(j).getStringCellValue().equals("N° LOT"))
                        lot.setNum_lot((int) row.getCell(j+1).getNumericCellValue());
                }
            }

        }

    }
    public void readvehicules(Sheet guru99Sheet ,FormulaEvaluator formulaEvaluator,Set<Vehicule> vehicules
            ,Modele modele,Lot lot)
    {
        int rowCount = guru99Sheet.getLastRowNum() - guru99Sheet.getFirstRowNum();
        for (int i = 7; i < rowCount + 1; i++) {
            Row row = guru99Sheet.getRow(i);
            CellType ty = formulaEvaluator.evaluateInCell(row.getCell(0)).getCellType();
            if(ty==BLANK) i++;
            else
            {   Vehicule v = new Vehicule();
                v.setOrdre((int) row.getCell(0).getNumericCellValue());
                v.setNum_Chassis( row.getCell(1).getStringCellValue());
                v.setNumengine( row.getCell(2).getStringCellValue());
                v.setCouleur( row.getCell(3).getStringCellValue());
                v.setLot(lot);
                v.setModele(modele);
                vehicules.add(v);
            }
        }
    }
    public void read(  Lot lot , Modele modele,Set<Vehicule> vehicules ) throws IOException {

        File file = new File(this.getChemin());
        FileInputStream inputStream = new FileInputStream(file);
        Workbook guru99Workbook = null;
        String fileExtensionName = this.getChemin().substring(this.getChemin().indexOf("."));
        if (fileExtensionName.equals(".xlsx")) { guru99Workbook = new XSSFWorkbook(inputStream);}
        else if (fileExtensionName.equals(".xls")) { guru99Workbook = new HSSFWorkbook(inputStream);}
        Sheet guru99Sheet = guru99Workbook.getSheetAt(0);
        FormulaEvaluator formulaEvaluator=guru99Workbook.getCreationHelper().createFormulaEvaluator();
        FirstInfo(modele,lot,guru99Sheet,formulaEvaluator);
        readvehicules(guru99Sheet,formulaEvaluator,vehicules,modele,lot);
    }
    public void readerfile( Lot lot , Modele modele,Set<Vehicule> vehicules) throws IOException {
        if(this.getExtension().equals(".xls"))
        {
            this.read(lot,modele,vehicules);
        }
        else if(this.getExtension().equals(".xlsx"))
        {
             System.out.println(".xlsx");
        }
        else
        {
             System.out.println("format inacceptable");
        }
        System.out.println("lot "+lot);
        System.out.println("modele "+ modele);
        System.out.println("vehicules " + vehicules);
    }




}
