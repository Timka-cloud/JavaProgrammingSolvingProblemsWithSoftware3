package Week3.Part1;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class WhichCountriesExport {
    public static void main(String[] args) {
        FileResource fl = new FileResource("exports_small.csv");
        CSVParser parser = fl.getCSVParser();

        listExporters( parser,"coffee");
    }
    public static void listExporters(CSVParser parser, String product){

        // for each row in CSV file
        for(CSVRecord record : parser){
            // Look at the "Exports" column
            String export = record.get("Exports");
            if(export.contains(product)){
                System.out.println(record.get("Country"));
            }


        }
    }

}
