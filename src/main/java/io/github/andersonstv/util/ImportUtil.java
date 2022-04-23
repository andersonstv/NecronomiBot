package io.github.andersonstv.util;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ImportUtil {
    final public static String fileSeparator = System.getProperty("file.separator");
    final public static String filepath = "src"+ fileSeparator +"main"+fileSeparator+"resources" + fileSeparator;

    public static Map<String, Integer> importMapCSV(String filename){
        Map<String, Integer> atts = new HashMap<>();
        try{
            Reader reader = new FileReader(filename);
            CSVReader csvReader = new CSVReader(reader);
            String[] row;
            while((row = csvReader.readNext()) != null){
                atts.put(row[0].toLowerCase(), Integer.parseInt(row[1]));
            }
            return atts;
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch(IOException | CsvValidationException cve){

        }
        return atts;
    }
}
