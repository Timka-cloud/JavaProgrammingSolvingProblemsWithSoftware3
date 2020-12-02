package Week3.Part1;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class HW1 {
    public static void main(String[] args) {
    tester();


    }
    public static void tester(){
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        String result = countryInfo(parser,"Nauru");
        System.out.println(result);
        System.out.println("=====");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton", "flowers");
        System.out.println("=====");
        parser = fr.getCSVParser();
        int num = numberOfExporters(parser, "cocoa");
        System.out.println(num);
        System.out.println("=====");
        parser = fr.getCSVParser();
        bigExporters(parser," $999,999,999,999");
    }

    public static String countryInfo(CSVParser parser, String countryName){
        for(CSVRecord record : parser){
            String temp = record.get("Country");
            if(temp.contains(countryName)){
                return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");

            }
        }
        return "NOT FOUND";
    }

    public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String n1 = record.get("Exports");
            String n2 = record.get("Exports");
            if(n1.contains(exportItem1) && n2.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }

        }
    }

    public static int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for(CSVRecord record : parser){
            String temp = record.get("Exports");

            if(temp.contains(exportItem)){
                record.get("Country");
                count++;
            }

        }
        return count;
    }

    public static void bigExporters(CSVParser parser, String val){
        for(CSVRecord record : parser){
            String temp = record.get("Value (dollars)");
            if(temp.length() > val.length()){
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

}
