package Week3.Part2;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVMaxTemp {
    public CSVRecord hottestHourInFile(CSVParser parser){
        // start with largestSoFar as nothing
        CSVRecord largestSoFar = null;
        //For each row (currentRow) in the CSV file
        for(CSVRecord currentRow : parser){

            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        } return largestSoFar;
    }

    public void testHottestInDay(){
        FileResource fl = new FileResource("part2Files/weather-2015-01-01.csv");

        CSVRecord largest = hottestHourInFile(fl.getCSVParser());
        System.out.println("hottest temp was " + largest.get("TemperatureF") +
                " at " + largest.get("TimeEST"));
    }

    public CSVRecord hottestInManyDays(){
        CSVRecord largestSoFar = null;
        //iterate over files
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            // use method to get largest in file
            CSVRecord currentRow = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentRow,largestSoFar);

        }
        return largestSoFar;
    }

    public void testHottestInManyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temp was " + largest.get("TemperatureF") +
                " at " + largest.get("DateUTC"));
    }

    public CSVRecord getLargestOfTwo (CSVRecord currentRow, CSVRecord largestSoFar){
        // if largestSoFar is nothing
        if(largestSoFar == null){
            largestSoFar = currentRow;
        }
        // otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            //Check if currentRow's temperature > largestSoFar's
            if(currentTemp > largestTemp){
                // if so update largestSoFar to currentRow
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public static void main(String[] args) {
        CSVMaxTemp csvMax = new CSVMaxTemp();
        csvMax.testHottestInManyDays();
    }
}
